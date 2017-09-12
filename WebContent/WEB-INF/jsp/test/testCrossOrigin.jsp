<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
    <body>本页面用于以跨域方式测试接口</body>
    <script type="text/javascript" src="${_contextPath}${_jsBase }/common/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">  
    var url = '${_contextPath }/api/convert.shtm';
    var host = 'http://192.168.190.216:8080'
	$.ajax({
		url: host + url,
		dataType:'jsonp',//JSONP 必须设置
		jsonpCallback:"successCallback",//JSONP 必须设置
		data: {
		    "jsonp":"successCallback",//JSONP 必须传递
		    "filename": 'svn.txt',
		    "path": 'E:/13/other',
		    "time": 1505197064831
		},
		success: function(data) {
			//jsonp回调处理(data已经是一个对象了，不能再使用eval)
			console.log(data);
		},
		error: function(data) {
		    console.log(data);
		}
	});
    </script>
</html>