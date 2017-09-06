package cn.lhl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchServlet extends HttpServlet {
    //模拟struts1的路由分发
	private static final long serialVersionUID = 8987064845052761921L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	private void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		StringBuffer uri = new StringBuffer("/WEB-INF/jsp");
		String reqUri = req.getRequestURI();
		String contextPath = req.getContextPath();
		uri.append(reqUri.replaceFirst(contextPath, "").replace(".shtm", ".jsp"));
		req.getRequestDispatcher(uri.toString()).forward(req, resp);
	}
}
