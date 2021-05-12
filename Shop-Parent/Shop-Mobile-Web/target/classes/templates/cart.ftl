
<!DOCTYPE html>
<!-- saved from url=(0069)http://m.mi.com/index.html#ac=product&op=view&commodity_id=1152800035 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="qian">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="apple-touch-icon-precomposed"
	href="">
<link rel="shortcut icon" href=""
	type="image/x-icon">
<meta name="description"
	content="千氏商城可以随时随地参与预约抢购，轻松下单购买，实时查询订单信息掌握物流状态，千氏商城还有新品信息通知推送功能，所有商品信息随身掌握。">
<meta name="keywords" content="千氏商城">
<link rel="stylesheet" type="text/css" href="css/order.css">
<title>千氏商城</title>
<style type="text/css">
object, embed {
	-webkit-animation-duration: .001s;
	-webkit-animation-name: playerInserted;
	-ms-animation-duration: .001s;
	-ms-animation-name: playerInserted;
	-o-animation-duration: .001s;
	-o-animation-name: playerInserted;
	animation-duration: .001s;
	animation-name: playerInserted;
}

@
-webkit-keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}

}
@
-ms-keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}

}
@
-o-keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}

}
@
keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}
}
</style>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<body style="display: block; background: rgb(255, 255, 255);">
	<div id="Cheader" style="">
		<div id="header" class="header_03">
			<div class="back">
				<a href="index" data-stat-id="1c3b2f8d3dcebd60"
					onclick="_msq.push([&#39;trackEvent&#39;, &#39;f086bb7168acc7da-1c3b2f8d3dcebd60&#39;, &#39;/home/&#39;, &#39;pcpid&#39;]);"
					class="arrow">首页</a>
			</div>
			<div class="tit" style="">
				<h3>购物车</h3>
			</div>
		</div>
	</div>
	<div id="wrapper" class="xm_app">
		<div id="viewport" class="viewport" style="">
			<div id="crumbs" class="crumbs" style="display: none;">
				<ul>
					<li><a href="index"><span>首页</span></a></li>
					<li><a >购物车</a></li>
				</ul>
			</div>
			<div class="box box_01">
								<form action="localPay">
									<table id="tbRecord">
										<thead>
											<tr>
												<th>编号</th>
												<th>名称</th>
												<th>卖点</th>
												<th>价格</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<#if itemList??>
												<#list itemList as p>
													<tr style="font-size: 18px">
														<td>${p_index+1}</td>
														<td>${p.title}</td>
														<#if p.sellPoint??>
															<td>${p.sellPoint}</td>
														<#else>
															<td>无</td>
														</#if>
														<td>${p.price}</td>
														<input name="price${p_index+1}" type="hidden" value="${p.price}">
														<td><a href="deleteByItemId?id=${p.id}" type="button" id="delete">删除 </a></td>
													</tr>
												</#list>
											</#if>																															
										</tbody>
									</table>
									<input type="submit" id="pay" value="付款">	
								</form>								
										<#if error??>
												<div>
													<span id="message_LOGIN_TOO_MUCH" style="color: red">
														${error}</span>
												</div>
										</#if>
			</div>
			<div id="productViewWeixinTip" class="weixin_share_tip"></div>
		</div>
	</div>
</body>
</html>