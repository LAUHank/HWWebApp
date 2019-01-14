package cn.lhl.web.action;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;
import cn.lhl.util.HttpUtil;

/**
 * Servlet implementation class HttpDownloadServlet
 */
@WebServlet("/HttpDownload")
public class HttpDownloadServlet extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(HttpDownloadServlet.class);
			
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HttpDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * http://127.0.0.1:8080/HWWebApp/HttpDownload?httpPath=0A1B&httpFileName=2019.doc
		 * http://127.0.0.1:8080/HWWebApp/HttpDownload?httpPath=&httpFileName=file.doc
		 */
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String httpPath = request.getParameter("httpPath");
		String httpFileName = request.getParameter("httpFileName");
		httpPath = (httpPath == null ? "" : httpPath.trim());
		httpFileName = (httpFileName == null ? "" : httpFileName.trim());
		
		String fileName = new String(httpFileName);
		String agent = request.getHeader("User-Agent");
        if (agent.contains("Firefox")) { // 火狐浏览器
            fileName = "=?UTF-8?B?" + new BASE64Encoder().encode(fileName.getBytes("utf-8")) + "?=";
        } else { // IE及其他浏览器
            fileName = URLEncoder.encode(fileName, "utf-8");
        }
        // 告诉浏览器这是一个下载文件的servlet
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        
        ServletOutputStream out = response.getOutputStream();
        
        try {
    		// HttpUtil hu = new HttpUtil("img.lhl.cn", 80, "lhl", "123");
    		HttpUtil hu = new HttpUtil();
    		hu.downloadFile(httpPath, httpFileName, out);
    	} catch (Exception e) {
    		LOGGER.error("异常", e);
    	}
		
	}
}
