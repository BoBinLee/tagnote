package kr.tagnote.article;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import kr.tagnote.common.Value;
import kr.tagnote.file.FileService;
import kr.tagnote.file.TagFile;
import kr.tagnote.tag.Tag;
import kr.tagnote.tag.TagArticle;
import kr.tagnote.tag.TagArticleRepository;
import kr.tagnote.tag.TagRepository;
import kr.tagnote.user.User;
import kr.tagnote.user.UserRepository;
import kr.tagnote.user.UserService;
import kr.tagnote.util.CommonUtils;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/article")
public class ArticleController {
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	@Autowired
	ArticleService articleService;
	@Autowired
	UserService userService;
	@Autowired
	FileService fileService;

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "")
	public String main(@RequestParam("name") String name, Model model, Principal principal) {
		Pageable pageable = new PageRequest(0, 100);
		List<Article.Response> articleDtos = null;
		Page<Article.Response> responses = null;
		Page<Article> articles = articleService.findByPage(pageable);

		articleDtos = modelMapper.map(articles, new TypeToken<List<Article.Response>>() {
		}.getType());
		responses = new PageImpl<Article.Response>(articleDtos);

		model.addAttribute("articles", responses);
		return "tag";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeView(@RequestParam(value = "id", required = false, defaultValue = "0") long id,
			@RequestParam(value = "name", required = false) String name, Model model, Principal principal) {
		User user = userService.findByEmail(principal.getName());
		Article.Response response = new Article.Response();
		Article article = null;

		if (id != 0)
			article = articleService.findById(id);
		if (article != null && article.getUser().getUserId() == user.getUserId()) {
			response = modelMapper.map(article, Article.Response.class);
			model.addAttribute("name", CommonUtils.urlEncode(name));
		}
		// logger.info("writeView : " + name);
		model.addAttribute("article", response);
		return "article";
	}

	// 같은 /write url로 할 경우, 에러발생함.
	@RequestMapping(value = "/write/submit", method = RequestMethod.POST)
	public String write(@ModelAttribute("article") Article.Request request,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "files", required = false) String files, Model model, Principal principal) {
		String response = (name != null) ? "redirect:/tag?name=" + name : "redirect:/tag/list";

		Article article = modelMapper.map(request, Article.class);
		article.setTagList(request.getTags());

		article = articleService.saveArticle(article, principal.getName());

		List fileList = CommonUtils.convertStrToList(files);
		
		logger.info("fileList : " + fileList.size());
		for (int i = 0; i < fileList.size(); i++) {
			if(fileList.get(i) instanceof String){
				long fileId = Integer.parseInt(fileList.get(i).toString());
				TagFile file = fileService.findById(fileId);
				
				file.setArticle(article);
				fileService.saveTagFile(file);
			}
		}
		return response;
	}

	@RequestMapping(value = "/send")
	@ResponseBody
	public Value<String> send(@RequestParam("artId") long artId, @RequestParam("uid") String uid) {
		Value<String> response = new Value<String>();
		response.setValue("fail");

		User user = userService.findByUid(uid);
		Article article = articleService.findById(artId);
		Article createArticle = null;

		if (user != null && article != null) {
			// 게시물 복사
			createArticle = new Article();
			createArticle.setSubject(article.getSubject());
			createArticle.setContent(article.getContent());
			createArticle.setTagList(Article.convertTagArticlesToTagList(article.getTagArticles()));
			createArticle.setUser(user);

			articleService.saveArticle(createArticle, user.getEmail(), article.getArtId());
			
			List<TagFile> files = article.getFiles();
			
			// 파일 복사
			for(int i=0; i<files.size(); i++){
				TagFile createTagFile = new TagFile();
				createTagFile.setArticle(createArticle);
				createTagFile.setName(files.get(i).getName());
				createTagFile.setSize(files.get(i).getSize());
				createTagFile.setType(files.get(i).getType());
				createTagFile.setBytes(restTemplate.getForObject(files.get(i).getUrl(), byte[].class));
				
				fileService.saveTagFile(createTagFile);
			}
			response.setValue("success");
		}
		return response;
	}

	@RequestMapping(value = "/paging")
	public ModelAndView pagingMain() {
		ModelAndView mv = new ModelAndView("article");
		Pageable pageable = new PageRequest(1, 10);
		List<Article.Response> articleDtos = null;
		Page<Article.Response> responses = null;
		Page<Article> articles = articleService.findByPage(pageable);

		articleDtos = modelMapper.map(articles, new TypeToken<List<Article.Response>>() {
		}.getType());
		responses = new PageImpl<Article.Response>(articleDtos);

		mv.addObject("articles", responses);
		return mv;
	}

	@RequestMapping(value = "/remove")
	public String remove(@RequestParam("name") String tagName, @RequestParam("id") long id) {
		articleService.deleteById(id);
		return "redirect:/tag?name=" + CommonUtils.urlEncode(tagName);
	}
}
