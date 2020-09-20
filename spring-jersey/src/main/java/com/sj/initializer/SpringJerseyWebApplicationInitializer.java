package com.sj.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.sj.config.RootConfig;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

public class SpringJerseyWebApplicationInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootApplicationContext = null;
		ContextLoaderListener contextLoaderListener = null;
		SpringServlet springServlet = null;

		rootApplicationContext = new AnnotationConfigWebApplicationContext();
		rootApplicationContext.register(RootConfig.class);
		contextLoaderListener = new ContextLoaderListener(rootApplicationContext);
		servletContext.addListener(contextLoaderListener);

		springServlet = new SpringServlet();
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("springServlet", springServlet);
		dynamic.setInitParameter("com.sun.jersey.config.property.packages", "com.sj.resources");
		dynamic.setLoadOnStartup(2);
		dynamic.addMapping("/api/*");
	}

}
