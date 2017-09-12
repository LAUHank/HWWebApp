<%@tag import="java.util.HashMap,java.util.Map"%>
<%@tag import="java.net.URLDecoder"%>
<%@tag import="java.io.File,org.apache.log4j.Logger"%>
<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="param" type="java.lang.Object" required="false"
	description="(Object) short for parameter, 执行SQL的参数对象MAP，一般可直接传入JSTL的param对象"%>

<%@ attribute name="var" type="java.lang.String" required="false"
	description="(String) 操作结果存放的变量名"%>
	
<%
    Logger log = Logger.getLogger(request.getLocalName()+request.getRequestURI());
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
