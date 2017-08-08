package cn.lhl.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class EncodingFilter implements Filter {
    private String tomcatDefaultEncoding = "ISO-8859-1";
    private String webXmlEncoding = "UTF-8";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // struts.i18n.encoding和request.setCharacterEncoding("UTF-8")只针对POST请求有效。对GET请求无效。
        // 所以对于GET请求必须对request.getParameter方法进行包装修改
        String method = request.getMethod();
        if ("GET".equals(method)) {
            request = new MyGetHttpServletRequestWrapper(request);
        } else if ("POST".equals(method)) {
            request.setCharacterEncoding(webXmlEncoding);
        }
        response.setCharacterEncoding(webXmlEncoding);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // web.xml中过滤器初始化参数获取
        String encoding = filterConfig.getInitParameter("encoding");
        if (StringUtils.isNotBlank(encoding)) {
            webXmlEncoding = encoding;
        }
    }

    // 自定义内部类用于包装request
    private class MyGetHttpServletRequestWrapper extends HttpServletRequestWrapper {
        public MyGetHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        private String StringConverter(String oldString) {
            String newString = null;
            if (StringUtils.isNotBlank(oldString)) {
                try {
                    newString = new String(oldString.getBytes(tomcatDefaultEncoding), webXmlEncoding);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return newString;
        }

        @Override
        public String getParameter(String name) {
            return StringConverter(super.getParameter(name));
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] oldValueArray = super.getParameterValues(name);
            String[] newValueArray = null;
            if (oldValueArray != null && oldValueArray.length > 0) {
                newValueArray = new String[oldValueArray.length];
                for (int i = 0; i < oldValueArray.length; i++) {
                    newValueArray[i] = StringConverter(oldValueArray[i]);
                }
            }
            return newValueArray;
        }
    }
}
