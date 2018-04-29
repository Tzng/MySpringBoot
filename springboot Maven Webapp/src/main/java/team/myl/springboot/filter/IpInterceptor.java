package team.myl.springboot.filter;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * IP拦截器
 * <p>
 * 标题：IpInterceptor
 * </p>
 * <p>
 * 描述：
 * </p>
 * 
 * @data 2018年3月30日 下午10:49:12
 * @author tangbin
 * @version
 */
public class IpInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(IpInterceptor.class);

	public void afterCompletion(HttpServletRequest arg0, HttpServletRequest arg1, Object arg2, Object arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）调用, 返回true 则放行， false 则将直接跳出方法
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String ip = getIpAddr(request);
		// 获取可以访问系统的白名单
		// String ipStr = PropertyUtil.getProperty("ipWhiteList");
		String ipStr = ("192.168.1.5");
		String[] ipArr = ipStr.split("\\|");
		List<String> ipList = java.util.Arrays.asList(ipArr);

		if (ipList.contains(ip)) {
			logger.info("the request ip is : " + ip);
			return true;
		} else {
			logger.error(ip + " is not contains ipWhiteList .......");
			response.setHeader("Content-type", "text/html;charset=UTF-8");// 向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
			String data = "您好，ip为" + ip + ",暂时没有访问权限，请联系管理员开通访问权限。";
			OutputStream stream = response.getOutputStream();
			stream.write(data.getBytes("UTF-8"));
			return false;
		}
	}

	/**
	 * 获取访问的ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
