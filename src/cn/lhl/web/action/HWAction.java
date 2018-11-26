package cn.lhl.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class HWAction
 */
@WebServlet("/HWAction")
public class HWAction extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(HWAction.class);
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HWAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ua = request.getHeader("User-Agent");
		LOGGER.error("Hello 我的 World - error");
		LOGGER.warn("Hello 我的 World - warn");
		LOGGER.info("Hello 我的 World - info");
		LOGGER.debug("Hello 我的 World - debug");
		LOGGER.warn("user-agent ="+ua);
		String ip = request.getRemoteAddr();
		LOGGER.warn("ip ="+ip);
		String uri = request.getRequestURI();
		LOGGER.warn("uri ="+uri);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
