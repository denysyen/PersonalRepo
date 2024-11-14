package com;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication
public class DemoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBackendApplication.class, args);
	}
    /**
	  * to accept request from an UI configuration
	  * connection back to front throught FilterRegistrationbean
	  * We register our filter with FilterRegistrationBean using
	  * the setFilter() method and can specify one or more URL patterns
	  * @return
	  */
	@Bean
	public FilterRegistrationBean<CorsFilter> simpleCorsFilter() { // to handle CORS pre-flight requests and to update the response.
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**",config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
