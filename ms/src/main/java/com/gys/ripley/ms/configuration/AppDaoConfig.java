package com.gys.ripley.ms.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.gys.ripley.ms.exception.MsException;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.gys.ripley.ms.dao.impl" })
public class AppDaoConfig {

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws MsException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(sigesDS());
		sessionFactory.setPackagesToScan(new String[] { "com.gys.ripley.ms.model" });
		sessionFactory.setHibernateProperties(additionalProperties());

		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	DataSource sigesDS() throws MsException {

		DataSource dataSource = null;
		JndiTemplate jndi = new JndiTemplate();
		try {
			dataSource = jndi.lookup("java:/sigesDS", DataSource.class);
			return dataSource;
		} catch (Exception e) {
			throw new MsException(1, e);
		}
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.datasource", "java:/sigesDS");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.setProperty("show_sql", "true");
		return properties;
	}
}
