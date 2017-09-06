<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <body>本页面用于以跨域方式测试接口</body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">  
    var url = '${pageContext.request.contextPath}/convert.shtm';
    var host = 'http://192.168.190.216:8080'
	$.ajax({
		url: host + url,
		dataType:'jsonp',//JSONP 必须设置
		jsonpCallback:"successCallback",//JSONP 必须设置
		data: {
		    "jsonp":"successCallback",//JSONP 必须传递
		    "type": 1,
		    "filename": 'svn.txt',
		    "path": 'E:/13/other',
		    "resID": 222,
		    "time": 1502182760621,
		    "key": '9df30cca91e43b08'
		},
		success: function(data) {
			//jsonp回调处理(data已经是一个对象了，不能再使用eval)
			console.log(data.code);
			console.log(data.msg);
		},
		error: function(data) {
		    console.log(data);
		}
	});
    </script>
</html>