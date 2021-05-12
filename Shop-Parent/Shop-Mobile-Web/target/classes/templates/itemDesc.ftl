
<!DOCTYPE html>
<!-- saved from url=(0069)http://m.mi.com/index.html#ac=product&op=view&commodity_id=1152800035 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="xiaomi">
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
				<h3>商品详情</h3>
			</div>
			<div class="nav">
				<ul>
					<li class="cart"><a
						href = "shoppingCart"
						data-stat-id="96c452cb9824c0df"
						onclick="_msq.push([&#39;trackEvent&#39;, &#39;f086bb7168acc7da-96c452cb9824c0df&#39;, &#39;/index.html#ac=shopping&amp;op=index&#39;, &#39;pcpid&#39;]);">购物车</a>
						<span id="ShoppingCartNum" style="display: none"></span></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="wrapper" class="xm_app">
		<div id="viewport" class="viewport" style="">
			<div id="crumbs" class="crumbs" style="display: none;">
				<ul>
					<li><a href="index"><span>首页</span></a></li>
					<li><a href="javascript:;">商品详情</a></li>
				</ul>
			</div>
			<div class="product_view">
				<div class="box box_01">
					<div class="product_swipe">
						<!-- 单品介绍图片 -->
						<div class="swipe" id="slider" style="visibility: visible;">
							<div class="swipe-wrap" style="width: 1000px;">
								<div data-index="0"
									style="width: 40%; left: 0px; transition-duration: 0ms; transform: translate(0px, 0px) translateZ(0px);">
									<img width="70%"
										src="${item['image']}">
								</div>

							</div>
							<div class="swipe-nav">
								<span class="on">&nbsp;</span> <span>&nbsp;</span> <span>&nbsp;</span>
							</div>
						</div>
					</div>
					<div class="product_info">
						<div class="info info_right">
							<h3 class="name">${item['title']}</h3>
							<div class="right">
								<div id="favorite_add" class="favorite_add">
									<span>收藏</span>
								</div>
								<div id="pro_share" class="share">
									<span>分享</span>
								</div>
							</div>
						</div>						
						<div class="price">${item['price']}元</div>
						<div class="acts"></div>
						<div class="star comment_star">
							<div class="star_box">
								<div class="star_bar">
									<div style="width: 100%" class="star_num"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="box box_02 tab_html">
					<div class="tab_nav">
						<ul>
							<li><a  class="tab_head on"
								data-show="productViewDiv" id="productViewBtn">物品详情</a></li>
							<li><a  class="tab_head"
								data-show="commentViewDiv" id="commentViewBtn">评价晒单</a></li>
							<li><a  class="tab_head"
								data-show="attrsViewDiv" id="attrViewBtn">参数及问题</a></li>
						</ul>
					</div>
					<div class="tab_product tab_item" id="productViewDiv">
						<div class="product_main">${itemDesc['itemdesc']}</div>
					</div>
					<input type="hidden" name="goods_id" id="goods_id"
						value="2152800032">
					<div class="product_addCart">
						<a  id="bt_sp"
							class="button active_button" href="addShopping?id=#{item['id']}">加入购物车</a>
					</div>
				</div>
			</div>
			<div id="productViewWeixinTip" class="weixin_share_tip"></div>
		</div>
	</div>
</body>
</html>