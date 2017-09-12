<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%-- 参数非空 --%>
<c:if test="${empty param.filename or empty param.path or empty param.time }">
	${param.jsonp }({"code":400,"msg":"param_invalid"})<lhlUtil:stopPage/>
</c:if>

<%-- 不可超时 --%>
<c:set var="nowTimeServer" value="<%=System.currentTimeMillis()%>" />
<c:if test="${(nowTimeServer - param.time) gt (1000 * 60 * 5)}">
	${param.jsonp }({"code":400,"msg":"timeout"})<lhlUtil:stopPage/>
</c:if>

<%-- 封装参数 --%>
<jsp:useBean id="bean" class="java.util.HashMap" scope="page"></jsp:useBean>
<c:set target="${bean}" property="type" value="${param.type}"/>
<c:set target="${bean}" property="filename" value="${param.filename}"/><%-- 文件名 xxx.wmv --%>
<c:set target="${bean}" property="path" value="${param.path}"/> <%-- /web/project/.../2017/08/05/ --%>
<c:set target="${bean}" property="resID" value="${param.resID}"/>

<%-- 执行转换 --%>
<c:catch var="exp">
	<tag:convert var="info" param="${bean}"/>
    <c:if test="${info.code eq 1 }">
        ${param.jsonp }({"code":200,"msg":"${info.msg }"})<lhlUtil:stopPage/>
    </c:if>
    ${param.jsonp }({"code":500,"msg":"${info.msg }"})<lhlUtil:stopPage/>
</c:catch>

<%-- 异常处理 --%>
<c:if test="${not empty exp }">
	${param.jsonp }({"code":500,"msg":"exception"})
    <tag:log msg="exception" err="${exp }" level="error"/>
</c:if>
