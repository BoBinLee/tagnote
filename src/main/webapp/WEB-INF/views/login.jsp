<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>

    <link rel="stylesheet" type="text/css" href="/resources/styles/common/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/common/bootstrap.min.css"/>
    <link rel="stylesheet/less" type="text/css" href="/resources/styles/common/common.less"/>
    <link rel="stylesheet/less" type="text/css" href="/resources/styles/login.less"/>
</head>
<body>
<div class="login container">
    <div class="box box-1"></div>
    <div class="box box-2"></div>
    <div class="box box-3"></div>
    <h3 class="box title">Tag Note</h3>
    <form action="/user/login/submit" method="post">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">
                	<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                </div>
                <input type="email" name="email" class="form-control" placeholder="example@example.com">
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
        
        <input type="submit" class="btn btn-default" value="Confirm" />
    
        <div class="form-etc">
            <div class="email-sign-up">
                <a href="#">Sign In Or Create Account!</a>
            </div>
            <div class="fb-login-button" data-scope="public_profile,email" onlogin="signinFacebookCallback()"  data-max-rows="1" data-size="large" data-show-faces="false"
                 data-auto-logout-link="false"></div>
            <div class="google-login-button" id="googleSigninButton">
              <span
                      class="g-signin"
                      data-callback="signinGoogleCallback"
                      data-clientid="94680954925-k6rvvoj4aui3od553lke3v3dtb8kcq3u.apps.googleusercontent.com"
                      data-cookiepolicy="single_host_origin"
                      data-scope="https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email"
                      data-width="iconOnly">
              </span>
            </div>
            <!--<fb:login-button scope="public_profile,email" onlogin="signinFacebookCallback();">
            </fb:login-button>-->
            <div id="fb-root"></div>
            <div id="status">
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="/resources/scripts/common/less.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/angular.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/facebook.js"></script>
<script type="text/javascript" src="/resources/scripts/common/google.js"></script>
<script type="text/javascript" src="/resources/scripts/login.js"></script>
</body>
</html>