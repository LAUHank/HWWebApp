<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <body>本页面用于以普通方式测试接口</body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">  
    var url = '${pageContext.request.contextPath}/convert.shtm';
	$.ajax({
		url: url,
		data: {
		    "type": 1,
		    "filename": 'svn.txt',
		    "path": 'E:/13/other',
		    "resID": 222,
		    "time": 1502182364630,
		    "key": 'a75df2d9e145f0ba'
		},
		success: function(data) {
			//json回调处理(data是文本，需要使用eval)
			var resObj = eval('('+$.trim(data)+')');
			console.log(resObj.code);
            console.log(resObj.msg);
		},
		error: function(data) {
		    console.log(data);
		}
	});
    </script>
</html>