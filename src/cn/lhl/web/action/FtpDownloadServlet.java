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

import cn.lhl.util.FtpUtil;
import sun.misc.BASE64Encoder;

/**
 * Servlet implementation class FtpDownloadServlet
 */
@WebServlet("/FtpDownload")
public class FtpDownloadServlet extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(FtpDownloadServlet.class);
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FtpDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * http://192.168.56.1:8080/HWWebApp/FtpDownload?ftpPath=0A1B&ftpFileName=2019.doc
		 * curl "http://192.168.56.1:8080/HWWebApp/FtpDownload?ftpPath=0A1B&ftpFileName=2019.doc" -o 2019.doc
		 * 
		 * ftp://192.168.56.1/
		 * ftp://127.0.0.1/
		 * ftp://www.lvruan.com/ hosts 127.0.0.1 www.lvruan.com
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
		String ftpPath = request.getParameter("ftpPath");
		String ftpFileName = request.getParameter("ftpFileName");
		
		String fileName = new String(ftpFileName);
		String agent = request.getHeader("User-Agent");
        if (agent.contains("Firefox")) { // 火狐浏览器
            fileName = "=?UTF-8?B?" + new BASE64Encoder().encode(fileName.getBytes("utf-8")) + "?=";
        } else { // IE及其他浏览器
            fileName = URLEncoder.encode(fileName, "utf-8");
        }
        // 告诉浏览器这是一个下载文件的servlet
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        
        // 连接ftp
        // FtpUtil.initFtpClient("192.168.56.1", 21, "lhl", "123");
        FtpUtil.initFtpClient();
        try (ServletOutputStream out = response.getOutputStream();) {
        	// 目录及文件名不能是中文
        	FtpUtil.downloadFile(ftpPath, ftpFileName, out);
        } finally {
        	// 下载完成要释放ftp连接
        	FtpUtil.releaseFtpClient();
        }
		
    	LOGGER.info("done");
	}
}
