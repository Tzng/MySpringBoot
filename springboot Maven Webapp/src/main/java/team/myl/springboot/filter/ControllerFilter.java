package team.myl.springboot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerFilter implements Filter {

	private final Logger logger = LoggerFactory.getLogger(ControllerFilter.class);

	@Override
	public void destroy() {
		logger.info("过滤器被销毁");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info("过滤器初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("过滤生效");

		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		String url = hrequest.getRequestURI().substring(hrequest.getContextPath().length());

		logger.info("本次过滤路径 :" + url);
		chain.doFilter(request, response);
		return;
	}
}
