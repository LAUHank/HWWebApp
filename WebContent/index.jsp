<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MyWebApp</title>
</head>
<body>
index<a href="/HWWebApp/download" target="_blank">下载含中文文件名的文件</a><br/>
<div id="dd">aaa</div>
<script type="text/javascript" src="${_contextPath }${_jsBase }/common/jquery/jquery-1.10.2.min.js"></script>
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
    //自己做的一些小实验
    //---- 函数懒加载 begin ----
    var ua = navigator.userAgent;
    function testLazy() {
        if(ua.indexOf('Firefox') > -1) {
            alert('init firefox');
            //firefox
            testLazy = function() {
                alert('firefox');
            }
        } else {
            alert('init');
            testLazy = function() {
                alert('other');
            }
        }
    }
    (function() {
        alert('匿名');
    })();
    $('#dd').click(function() {
        testLazy();
    });
  //---- 函数懒加载 end ----
    //---- 模拟 setInterval begin ----
    function printLog(msg) {
        console.log(new Date() + ' - '+msg+' - ' +Math.random());
    }
    var msg = '模拟 setInterval';
    var interval = 1000;
    setTimeout(function() {
        printLog(msg);
        setTimeout(arguments.callee, interval);
    }, interval);
    //---- 模拟 setInterval end ----
</script>
</body>
</html>