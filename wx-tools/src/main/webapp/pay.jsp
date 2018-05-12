<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="mobile-agent"
	content="format=html5;url=http://ohsame.com/">
<meta name="renderer" content="webkit">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>微信支付页面</title>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
<script src="<%= path%>/resources/wx/jweixin-1.2.0.js" type="text/javascript" ></script>
<script src="<%= path%>/resources/wx/config.js" type="text/javascript" ></script>

<script type="text/javascript">
	$(function(){
		jssdkConfig("http://antgan.hicp.net/wx-tools/pay.jsp","chooseWXPay");
		
	});
</script>
</head>
<body>

</body>
</html>