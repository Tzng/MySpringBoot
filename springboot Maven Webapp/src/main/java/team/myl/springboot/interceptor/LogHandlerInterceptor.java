package team.myl.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogHandlerInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(LogHandlerInterceptor.class);

	/**
	 * controller 在请求处理之前调用，在这里进行用户权限判断
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("------preHandle-----");
		String userToken = request.getParameter("userToken");
		// 获取上下文路径,如:/edu
		String path = request.getContextPath();
		// 如：http://localhost:8080/edu
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		// 目前访问的相对路径,如：/manager/main/menuxml.jsp
		String nowURl = request.getServletPath();
		// token验证
		/*
		 * if (("").equals(userToken) || userToken == null) { if
		 * (nowURl.indexOf("validation") > 0) { logger.info("用户进行登录操作"); return
		 * true; } else { logger.info("用户操作被拦截"); return false; } } else { //
		 * 用户锁 logger.info("允许用户访问:" + "测试"); logger.info("正在访问URL:" + basePath
		 * + nowURl); logger.info("应用路径:" + path); logger.info("基础路径:" +
		 * basePath); logger.info("当前路径:" + nowURl); return true;//
		 * 如果false，停止流程，api被拦截 }
		 */
		return true;
	}

	/**
	 * controller 请求处理之后进行调用，但是在视图被渲染之前
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("------postHandle-----");
	}

	/**
	 * 页面渲染之后调用，一般用于资源清理操作
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("------afterCompletion-----");

	}

}