<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html ng-app="setting">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Settings</title>

    <link rel="stylesheet" type="text/css" href="/resources/styles/common/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/common/bootstrap.min.css"/>
    <link rel="stylesheet/less" type="text/css" href="/resources/styles/common/common.less"/>
    <link rel="stylesheet/less" type="text/css" href="/resources/styles/setting.less"/>
</head>
<body>
<div ng-controller="settingController" class="container setting" >
    <div class="main panel panel-default">
        <div class="panel-heading">
        	<span><img src="/resources/img/common-settings-gear.png" /></span>
        	<div class="pull-right"><a href="/tag/list"><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span></a></div>
        </div>
        <div class="panel-body">
            <form action="/user/setting/submit" method="post">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">
                        	<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                        </div>
                        <input type="email" name="email" class="form-control" placeholder="example@example.com" value="${ user.email }" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">
                         	<span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
                        </div>
                        <input type="password" name="password" class="form-control" placeholder="password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" name="uid" ng-model="uid" class="form-control" placeholder="TagNote ID" ng-init="uid='${ user.uid }'">
                        <a class="input-group-addon btn btn-default" href="#" ng-click="generate()">Generate</a>
                    </div>
                </div>
                <input type="submit" class="btn btn-default" value="Save" />
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="/resources/scripts/common/less.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/angular.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/common.js"></script>
<script type="text/javascript" src="/resources/scripts/setting.js"></script>
</body>
</html>