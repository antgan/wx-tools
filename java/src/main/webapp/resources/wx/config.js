/**
 * 获取js-sdk接口的config方法
 */
function jssdkConfig(url , jsApiList){
	$.ajax({
		type : "GET",
		url :"./../wx-tools/jssdk/config",
		dataType : "html",
		data:{
//			"url":url,
//			"jsApi":jsApiList
		},
		success : function(data) {
			alert(data);
			var obj = eval("("+data+")");
			//调用config.js，获取js-sdk接口
			config(true, obj.appid, obj.timestamp,obj.noncestr, obj.signature, obj.jsApiList);
		},
		error : function(data) {
			alert("获取jssdk参数失败");
		}
	});
	
}

//获取js-sdk接口
function config(debug, appId, timestamp, nonceStr, signature, jsApiList){
	wx.config({
	    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: appId, // 必填，公众号的唯一标识
	    timestamp: timestamp, // 必填，生成签名的时间戳
	    nonceStr: nonceStr, // 必填，生成签名的随机串
	    signature: signature,// 必填，签名，见附录1
	    jsApiList: jsApiList // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
}