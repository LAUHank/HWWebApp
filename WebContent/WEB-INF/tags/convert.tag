<%@tag import="java.util.HashMap,java.util.Map,java.util.Enumeration"%>
<%@tag import="java.net.URLDecoder"%>
<%@tag import="java.io.File,org.apache.log4j.Logger"%>
<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="param" type="java.lang.Object" required="false"
	description="(Object) short for parameter, 执行SQL的参数对象MAP，一般可直接传入JSTL的param对象"%>

<%@ attribute name="var" type="java.lang.String" required="false"
	description="(String) 操作结果存放的变量名"%>
	
<%
    Logger log = Logger.getLogger(request.getLocalName()+request.getRequestURI());

	Enumeration<String> headerNames = request.getHeaderNames();
	while (headerNames.hasMoreElements()) {
	    String key = (String) headerNames.nextElement();
	    if(!("cookie".equalsIgnoreCase(key))) {//目前暂定只透传cookie
	        continue;
	    }
	    String value = request.getHeader(key);
	    value = value.replaceAll("[\\n\\r]", "");//因为header中有回车换行要进行替换
	    System.out.println(key+" = ["+value+"]");
	}	
	
	Cookie[] cookies = request.getCookies();
	for(Cookie cookie : cookies) {
	    String name = cookie.getName();
	    String value = cookie.getValue();
	    String domain = cookie.getDomain();
	    String cookiePath = cookie.getPath();
	    int ma = cookie.getMaxAge();
	    System.out.print("name = ["+name+"] ");
	    System.out.print("value = ["+value+"] ");
	    System.out.print("domain = ["+domain+"] ");
	    System.out.print("cookiePath = ["+cookiePath+"] ");
	    System.out.print("maxAge = ["+ma+"] ");
	    System.out.println();
	}
	
	int code = -1;
	String msg = "tag_error";
    HashMap<String, Object> map = (HashMap<String, Object>)param;
	
	Object fileNameObj = map.get("filename");
	Object pathObj = map.get("path");
	
	String fileName = fileNameObj.toString();
	String path = pathObj.toString();

	boolean fileFlag = false;
	File file = new File(path + File.separator + fileName);
	fileFlag = file.exists();
	if(!fileFlag) {
	    code = -2;
	    msg = "file_not_exists";
	}
	
	String cmd = null;
	if(fileFlag) {  //执行转换脚本
	    cmd = "sh /web/webserver/convert/video_convert2mp4.sh " + path + " " + fileName;
	    log.debug(cmd);
	    code = 1;
	    msg = "success";
	} 
	Map<String, Object> resultMap = new HashMap<String, Object>();
	resultMap.put("code", code);
	resultMap.put("msg", msg);
	jspContext.setAttribute(var, resultMap, PageContext.REQUEST_SCOPE);
%>
