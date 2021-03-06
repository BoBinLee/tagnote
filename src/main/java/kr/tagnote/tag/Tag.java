package kr.tagnote.tag;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
	@SequenceGenerator(name = "auto_gen", sequenceName = "tag_tag_id_seq")
	@Column(name = "tag_id")
	private int tagId;
	@Column(unique = true)
	private String name;
	private String color;
	private Timestamp created;
	private Timestamp updated;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tag", cascade = CascadeType.ALL)
	private List<TagArticle> tagArticles;
	
	@PrePersist
	public void onCreate() {
		created = updated = new Timestamp((new Date()).getTime());
	}

	@PreUpdate
	public void onUpdate() {
		updated = new Timestamp((new Date()).getTime());
	}
	
	@Data
	public static class Request {
		private String name;
		private String color;
	}
	
	@Data
	public static class Reponse {
		private int tagId;
		private String name;
		private String color;
	}
}
