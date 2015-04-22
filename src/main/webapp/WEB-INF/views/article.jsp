<html ng-app="article">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Article</title>

    <link rel="stylesheet" type="text/css" href="/resources/styles/common/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/common/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/common/ng-tags-input.min.css"/>
    <link rel="stylesheet/less" type="text/css" href="/resources/styles/common/common.less"/>
    <link rel="stylesheet/less" type="text/css" href="/resources/styles/article.less"/>
</head>
<body ng-controller="articleController">
<div class="article container">
    <div class="main panel panel-default">
        <div class="panel-heading"><span>Article</span></div>
        <div class="panel-body">
            <form>
                <!--<pre>Model: {{tags}}</pre>-->
                <div class="form-group">
                    <label>Title</label>
                    <input type="text" class="form-control" name="subject"/>
                </div>
                <div class="form-group">
                    <label>Tag</label>
                    <tags-input ng-model="tags">
                        <auto-complete source="getTag($query)"></auto-complete>
                    </tags-input>
                    <input type="hidden" name="tags" value="{{tags}}"/>
                </div>
                <div class="form-group">
                    <label>Content</label>
                    <textarea class="form-control" rows="5"></textarea>
                </div>
                <div class="form-group pull-right">
                    <input type="submit" class="btn btn-default" value="Write"/>
                    <input type="button" class="btn btn-default" value="Cancel"/>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="/resources/scripts/common/less.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/angular.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/ui-bootstrap-tpls-0.12.1.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/ng-tags-input.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/common.js"></script>
<script type="text/javascript" src="/resources/scripts/article.js"></script>
</body>
</html>