package team.myl.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import team.myl.springboot.filter.ControllerFilter;
import team.myl.springboot.interceptor.LogHandlerInterceptor;

@SpringBootApplication
@MapperScan(value = "team.myl.springboot.mapper")
public class ExampleSpringBoot {

	public static void main(String[] args) {
		SpringApplication.run(ExampleSpringBoot.class, args);
	}

	@Configuration
	public class MyWebConfig extends WebMvcConfigurerAdapter {

		/**
		 * 注册 拦截器
		 */
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new LogHandlerInterceptor());
		}

		/**
		 * <p>
		 * 描述: 注册过滤器
		 * </p>
		 * 
		 * @return FilterRegistrationBean 返回类型
		 * @author: tangbin
		 * @version: V1.0
		 * @Date: 2018年4月30日 上午9:39:31
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Bean
		public FilterRegistrationBean myFilter() {
			FilterRegistrationBean myFilter = new FilterRegistrationBean();
			myFilter.addUrlPatterns("/*");
			myFilter.setFilter(new ControllerFilter());
			return myFilter;
		}
	}
}