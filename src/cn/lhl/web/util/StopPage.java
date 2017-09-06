package cn.lhl.web.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class StopPage extends TagSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 754111734589893129L;

	public int doEndTag() throws JspException {
		return SKIP_PAGE;
	}

}
