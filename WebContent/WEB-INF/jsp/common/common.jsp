<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.lhl.cn/taglib/lhlUtil" prefix="lhlUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--当前Context --%>
<c:set var="_contextPath" value="<%=request.getContextPath() %>" scope="page"></c:set>
<%--引用js文件，通过修改value为/js_min 切换成部署路径 --%>
<c:set var="_jsBase" value="/js" scope="page"></c:set>
<%--引用css文件，通过修改value为/css_min 切换成部署路径 --%>
<c:set var="_cssBase" value="/css" scope="page"></c:set>
