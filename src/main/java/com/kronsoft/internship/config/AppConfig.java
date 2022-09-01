package com.kronsoft.internship.config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
	  
//	  @Bean
//	  public WebMvcConfigurer corsConfigurer() {
//		 return new WebMvcConfigurer() {
//			 @Override
//			 public void addCorsMappings(CorsRegistry registry) {
//				 registry.addMapping("/**").allowedOrigins("http://localhost:4200");
//			 }
//		 };
//	  }
	  
	  @Bean
	  public FilterRegistrationBean<CorsFilter> corsFilterBean() {
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowCredentials(true);

			List<String> originsPatterns = new ArrayList<>();
			originsPatterns.add("http://localhost:4200");
			config.setAllowedOriginPatterns(originsPatterns);

			config.addAllowedHeader("*");
			config.addAllowedMethod("*");
			source.registerCorsConfiguration("/**", config);
			FilterRegistrationBean<CorsFilter> bean = 
					new FilterRegistrationBean<>(new CorsFilter(source));
			bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
			return bean;
	  }
	  
}
