package cn.lhl.web.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = "commons-lang-2.6-bin-中文.zip";
        String ua = request.getHeader("user-agent");
        downfile(ua, filename, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

    private void downfile(String ua, String fileName, HttpServletResponse response) throws IOException {
        String fullFileName = getServletContext().getRealPath("/WEB-INF/download/" + fileName);
        String agent = ua;// request.getHeader("user-agent");
        if (agent.contains("Firefox")) { // 火狐浏览器
            fileName = "=?UTF-8?B?" + new BASE64Encoder().encode(fileName.getBytes("utf-8")) + "?=";
        } else { // IE及其他浏览器
            fileName = URLEncoder.encode(fileName, "utf-8");
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        InputStream in = new BufferedInputStream(new FileInputStream(fullFileName));
        OutputStream out = response.getOutputStream();
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        in.close();
        out.close();
    }
}
