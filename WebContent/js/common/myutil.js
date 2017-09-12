/**
 * 一些常用的javascript函数工具
 */
//给指定url后面拼接查询字符串
//字符串的name与value必须进行encodeURIComponent
function addQueryStringArg(url, name, value) {
	if(url.indexOf('?') == -1) {
		url += '?';
	} else {
		url += '&';
	}
	url += encodeURIComponent(name) + '=' + encodeURIComponent(value);
	return url;
}
//获取指定cookie domain + path + name唯一确定一个cookie
function getCookie(name) {
	var cookieName = encodeURIComponent(name) + '=',
	    cookieStart = document.cookie.indexOf(cookieName),
	    cookieValue = null;
	if(cookieStart > -1) {
		var cookieEnd = document.cookie.indexOf(';', cookieStart);
		if(cookieEnd == -1) {
			cookieEnd = document.cookie.length;
		}
		cookieValue = decodeURIComponent(document.cookie.substring(cookieStart + cookieName.length, cookieEnd));
	}
	return cookieValue;
}
//设置cookie没有添加有则修改
function setCookie(name, value, expires, path, domain, secure) {
	var cookieText = encodeURIComponent(name) + '=' + encodeURIComponent(value);
	if(expires instanceof Date) {
		cookieText += '; expires=' + expires.toGMTString();
	}
	if(path) {
		cookieText += '; path=' + path;
	}
	if(domain) {
		cookieText += '; domain=' + domain;
	}
	if(secure) {
		cookieText += '; secure';
	}
	document.cookie = cookieText;
}
//删除cookie
function delCookie(name, path, domain, secure) {
	setCookie(name, '', new Date(0), path, domain, secure);
}
