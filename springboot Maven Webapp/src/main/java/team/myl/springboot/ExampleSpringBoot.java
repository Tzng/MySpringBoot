package team.myl.springboot;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.pagehelper.PageHelper;

import team.myl.springboot.interceptor.LogHandlerInterceptor;
import team.myl.springboot.interceptor.MyInterceptor;

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
	}

	// mvc控制器
	// @Configuration
	static class WebMvcConfigurer extends WebMvcConfigurerAdapter {
		// 增加拦截器
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new MyInterceptor()) // 指定拦截器类
					.addPathPatterns("/user"); // 指定该类拦截的url
		}
	}

	// 配置mybatis的分页插件pageHelper
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
