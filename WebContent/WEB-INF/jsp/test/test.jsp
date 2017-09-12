<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
    <body>本页面用于以普通方式测试接口</body>
    <script type="text/javascript" src="${_contextPath }${_jsBase }/common/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">  
    var url = '${_contextPath }/api/convert.shtm';
	$.ajax({
		url: url,
		data: {
		    "filename": 'svn.txt',
		    "path": 'E:/13/other',
		    "time": 1505197064831,
		},
		//dataType: 'json',
		success: function(data) {
			//json回调处理(data是文本，需要使用eval)
			var resObj = eval('('+$.trim(data)+')');
			console.log(resObj);
		},
		error: function(data) {
		    console.log(data);
		}
	});
    </script>
</html>