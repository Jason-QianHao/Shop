<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<meta content="yes" name="apple-mobile-web-app-capable" />
<!-- ios系统的私有标签，它指定在web app状态下，ios设备中顶端的状态条的颜色 -->
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!-- 设备浏览网页时对数字不启用电话功能 -->
<meta content="telephone=no,email=no" name="format-detection" />
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
<link rel="stylesheet" href="css/common.css">
<!-- 自适应样式单 -->
<link rel="stylesheet" href="css/adaptive.css">
<link rel="stylesheet" href="css/login.css">
<body class="zh_CN">
	<div class="layout">
		<div class="nl-content">
			<div class="nl-login-title" id="custom_display_1">
				<a> <img
					src="images/qian.png"
					width="100">
				</a>
			</div>

			<h1 class="nl-login-title" id="custom_display_256">
				<span id="message_LOGIN_TITLE">千氏商城</span>
			</h1>
			<h2 class="nl-login-title lsrp-appname display-custom-hide"
				id="lsrp_appName"></h2>
			<div class="nl-frame-container">
				<div class="ng-form-area show-place" id="form-area">
					<form method="post" action="login" id="miniLogin"">
						<div class="shake-area" id="shake_area" style="z-index: 30;">
							<div class="enter-area display-custom-hide" id="revalidate_user">
								<p class="revalidate-user-name" id="revalidate_user_name"></p>
							</div>
							<div class="enter-area" id="enter_user">
								<input type="text" name="name"
									class="enter-item first-enter-item"
									id="miniLogin_name" autocomplete="off"
									placeholder="用户名/邮箱/手机号码"> <i
									class="placeholder hide" id="message_INPUT_IDENTITY">用户名/邮箱/手机号码</i> <span
									class="error-tip"><em class="error-ico"></em><span
									class="error-msg"></span></span>
							</div>
							<div class="enter-area" style="z-index: 20;">
								<input type="password" name="password"
									class="enter-item last-enter-item" id="miniLogin_pwd"
									autocomplete="off" data-rule="" placeholder="密码"> <i
									class="placeholder hide" id="message_INPUT_PASSWORD">密码</i> <span
									class="error-tip"><em class="error-ico"></em><span
									class="error-msg"></span></span>
							</div>
						</div>
						<#if error ??>
						<div>
							<span id="message_LOGIN_TOO_MUCH" style="color: red">${error}</span>
						</div>
						</#if>
						<input class="button orange" type="submit"
							id="message_LOGIN_IMMEDIATELY" value="立即登录">

						<div class="ng-foot clearfix">
							<div class="ng-link-area">
								<span> <a
									href="#"
									id="other_method_default">QQ联合登录</a><span> | </span>
								</span> 
								<span id="custom_display_16"> <a
									href="#" id="other_method">其他方式登录</a> <span> | </span>
								</span> 
								<span id="custom_display_64"> <a
									href="#"
									id="message_FORGET_PASSWORD" target="_blank">忘记密码？</a>
								</span>
								<div class="third-area hide" id="third_area"></div>
							</div>
						</div>
						<span id="custom_display_128"> <a
							href="localRegist"
							class="button" id="message_REGISTER">注册帐号</a>
						</span> <span id="custom_display_8"> 
					</form>
				</div>
			</div>

			<div class="web-info">
				<p class="web-info-content" id="web_info_content"></p>
			</div>
		</div>

		<#include "common/bottom.ftl">
</body>
</html>