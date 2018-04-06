package team.myl.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogHandlerInterceptor implements HandlerInterceptor {

	/**
	 * controller 在请求处理之前调用，在这里进行用户权限判断
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("------preHandle-----");
		// 获取上下文路径,如:/edu
		String path = request.getContextPath();
		// 如：http://localhost:8080/edu
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		// 目前访问的相对路径,如：/manager/main/menuxml.jsp
		String nowURl = request.getServletPath();

		// 用户锁
		// log.info("用户的锁值:" + lock);
		System.out.println("正在访问URL:" + basePath + nowURl);
		System.out.println("应用路径:" + path);
		System.out.println("基础路径:" + basePath);
		System.out.println("当前路径:" + nowURl);
		return true;// 如果false，停止流程，api被拦截
	}

	/**
	 * controller 请求处理之后进行调用，但是在视图被渲染之前
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("------postHandle-----");
	}

	/**
	 * 页面渲染之后调用，一般用于资源清理操作
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("------afterCompletion-----");

	}

}