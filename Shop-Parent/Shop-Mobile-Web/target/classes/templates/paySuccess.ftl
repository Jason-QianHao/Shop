<html>
	<script type='text/javascript'>
		var num=6;
		function calc(){
		if(num>0){   
		num--;       
		document.getElementById('second').innerHTML=num;       
		}else{
		location.href='afterPay'       
		}
		setTimeout('calc()',1000);
		}
	</script>
	 
	<body onload="calc()">
		<h2>
			<div>支付成功</div>
			即将跳转主页...<div id='second'>5</div>
		</h2>
	</body>
</html>