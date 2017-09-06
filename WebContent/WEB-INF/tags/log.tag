<%@ tag language="java" pageEncoding="utf-8"%>
<%@tag import="javax.servlet.jsp.tagext.TagSupport"%>
<%@tag import="org.apache.log4j.Logger"%>
<%@ attribute 
	name="msg" 
	type="java.lang.String" 
	required="true" 
	description="LOG的信息"
%>
<%@ attribute 
	name="level" 
	type="java.lang.String" 
	required="false" 
	description="LOG等级"
%>
<%@ attribute 
	name="err" 
	type="java.lang.Exception" 
	required="false" 
	description="Exception对象"
%>
<%@ attribute 
	name="loc" 
	type="java.lang.String" 
	required="false" 
	description="出错页面"
%>
<%
	Logger log = null;
	if(null==loc||"".equals(loc)){
		loc = request.getLocalName()+request.getRequestURI();
	}else{
		loc = request.getLocalName()+request.getRequestURI()+"/line:"+loc;
	}
	log = Logger.getLogger(loc);
	msg = System.getProperty("line.separator") + msg;
	if(null!=err){
		log.error(msg, err);
	}else{
		if ("info".equals(level)) {
			if(log.isInfoEnabled()){
				log.info(msg);
			}
		} else if ("error".equals(level)) {
			log.error(msg);
		} else {
			if(log.isDebugEnabled()){
				log.debug(msg);
			}
		}
	}
%>