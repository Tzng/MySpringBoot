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

import org.springframework.stereotype.Component;

/**
 * 
 * 跨域过滤器
 * 
 * @author meng
 * @version
 * @since 2016年6月19日
 */
@Component
public class CorsFilter implements Filter {

	final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		String[] allowDomain = { "http://192.168.1.213:8000", "http://localhost:8000", "http://localhost:8080" };
		String originHeader = ((HttpServletRequest) req).getHeader("Origin");

		boolean flag = false;

		for (String url : allowDomain) {
			if (url.equals(originHeader)) {
				flag = true;
				break;
			}
		}
		logger.info("响应请求头");
		response.setHeader("Access-Control-Allow-Origin", flag ? originHeader : "null");// 设置允许的域名
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		// 表示是否允许发送Cookie
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization, include, xhrfields");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP
		response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0.

		response.setDateHeader("Expires", 0);
		System.out.println("*********************************过滤器被使用**************************");
		chain.doFilter(req, res);
		return;
	}

	public boolean verMethods(HttpServletRequest sreq) {
		boolean flag = false;
		String methods = sreq.getMethod();
		logger.info("请求的类型为：" + methods);
		return false;
	}

	public void init(FilterConfig filterConfig) {

	}

	public void destroy() {

	}
}