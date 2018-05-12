/**
 * 支付js接口
 */
var invokePay;
//获得统一下单参数
function getOrderParam(objStr){
	alert(objStr);
	$.ajax({
		type : "POST",
		url : $("#rootPath").val()+ "/jssdk/config",
		dataType : "html",
		data:{},
		success : function(data) {
			console.log(data);
			invokePay = eval("("+data+")");
			console.log(invokePay);
			if (typeof (WeixinJSBridge) == "undefined"){
				   if( document.addEventListener ){
				       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
				   }else if (document.attachEvent){
				       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
				       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
				   }
				}else{
				   onBridgeReady();
			} 
			//调起支付方法
			function onBridgeReady(){
			   WeixinJSBridge.invoke(
			       'getBrandWCPayRequest', {
			           "appId":invokePay.appId,     //公众号名称，由商户传入     
			           "timeStamp":invokePay.timeStamp,         //时间戳，自1970年以来的秒数     
			           "nonceStr":invokePay.nonceStr, //随机串     
			           "package" :"prepay_id="+invokePay.prepay_id,     
			           "signType" : invokePay.signType,         //微信签名方式：     
			           "paySign" : invokePay.paySign //微信签名 
			       },
			       function(res){ 
			    	   $("#result").html(res.err_msg);
			    	// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
			           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
			        	   afterPaySave(objStr);
			           }else{
			        	   alert("取消支付");
			           }
			           
			       }
			   ); 
			}
			
		},
		error : function(data) {
			alert("统一下单失败");
		}
	});
}

//支付成功后，保存支付商品
function afterPaySave(objStr){
	$.ajax({
		type : "POST",
		url : $("#rootPath").val()+ "/pay/after",
		dataType : "html",
		data:{
			"arrayStr": objStr
		},
		success : function(data) {
			
		}
   })
}



