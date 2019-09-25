package com.bbeauvais.hibernateexample;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class JPAUtils {

	private EntityManagerFactory entityManagerFactory;
	private Collection<Class<?>> annotatedClass;

	public JPAUtils(Class<?>... annotatedClass) {
		this.annotatedClass = Arrays.asList(annotatedClass);
	}

	public EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			initEntityManagerFactory();
		}

		return entityManagerFactory.createEntityManager();
	}

	private void initEntityManagerFactory() {
		Properties hibernateProperties = new Properties();

		hibernateProperties.put(Environment.DRIVER, org.h2.Driver.class.getName());
		hibernateProperties.put(Environment.URL, "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		hibernateProperties.put(Environment.DIALECT, org.hibernate.dialect.H2Dialect.class.getName());
		hibernateProperties.put(Environment.SHOW_SQL, Boolean.TRUE.toString());
		hibernateProperties.put(Environment.HBM2DDL_AUTO, "create");

		Configuration hibernateConfiguration = new Configuration();
		hibernateConfiguration.setProperties(hibernateProperties);

		annotatedClass.forEach(hibernateConfiguration::addAnnotatedClass);

		ServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySettings(hibernateConfiguration.getProperties()).build();

		entityManagerFactory = hibernateConfiguration.buildSessionFactory(registry);
	}
}
