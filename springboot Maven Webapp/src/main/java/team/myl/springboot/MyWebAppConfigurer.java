package team.myl.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import team.myl.springboot.filter.MyInterceptor1;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

	@Autowired
	MyInterceptor1 myInterceptor1;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(myInterceptor1).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}