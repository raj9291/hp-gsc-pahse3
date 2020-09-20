package com.sj.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("classpath:db.properties")
@EnableJpaRepositories(basePackages = { "com.sj.repositories" })
public class PersistenceConfig {
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = null;

		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driverClassname"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}

	@Bean(autowire = Autowire.BY_NAME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = null;
		Properties jpaProperties = null;

		jpaProperties = new Properties();
		jpaProperties.put("show_sql", "true");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		jpaProperties.put("format_sql", "true");
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");

		localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
		localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		return localContainerEntityManagerFactoryBean;
	}

	@Bean(autowire = Autowire.BY_NAME)
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
}
