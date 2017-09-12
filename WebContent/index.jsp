<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MyWebApp</title>
</head>
<body>
index<a href="/HWWebApp/download" target="_blank">下载含中文文件名的文件</a>
</body>
<script type="text/javascript" src="${_contextPath }${_jsBase }/common/myutil.js"></script>
<script type="text/javascript">  
    var curUrl = window.location.href;
    var name = 'gotoURL';
    var value = 'http://www.somewebsite.com?a=b&c=d&e=f';
    var newUrl = addQueryStringArg(curUrl, name, value);
    'console' in window && console.log('new url = ' + newUrl);
    var cookieName = 'mycookie';
    setCookie(cookieName, 'mycv2', null, '/');
    //delCookie(cookieName, '/');
    var cVal = getCookie(cookieName);
    'console' in window && console.log(cookieName + ' = ' + cVal);
</script>
</html>