package com.kronsoft.internship.config;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kronsoft.internship.constants.FilterConstants;

@Configuration
@EnableJpaRepositories("com.kronsoft.internship.dao")
@EnableTransactionManagement // activeaza managementul de tranzactii (vezi @Transactional)
public class AppConfig {
	
	  @Bean
	  @ConditionalOnMissingBean
	  public PlatformTransactionManager transactionManager(
	      ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
	      JpaTransactionManager transactionManager = new JpaTransactionManager() {
	      @Override
	      protected EntityManager createEntityManagerForTransaction() {
	        final EntityManager entityManager = super.createEntityManagerForTransaction();
	        Session session = entityManager.unwrap(Session.class);
	        session.enableFilter(FilterConstants.PATIENTS_OVER_EIGHTEEN);
	        return entityManager;
	      }
	    };
	    transactionManagerCustomizers.ifAvailable((customizers) -> customizers.customize(transactionManager));
	 
	    return transactionManager;
	  }
	  
	  @Bean
	  public WebMvcConfigurer corsConfigurer() {
		  return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**").allowedOrigins("http://localhost:4200");
				}
			};
	  }
	  
}
