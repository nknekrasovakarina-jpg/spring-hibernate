package hiber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "hiber")
public class AppConfig {

   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource ds = new DriverManagerDataSource();
      ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
      ds.setUrl("jdbc:mysql://localhost:3306/spring_hibernate_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
      ds.setUsername("root");
      ds.setPassword("password");
      return ds;
   }

   @Bean
   public LocalSessionFactoryBean sessionFactory() {
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(dataSource());
      sessionFactory.setPackagesToScan("hiber.model");

      Properties hibernateProperties = new Properties();
      hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
      hibernateProperties.put("hibernate.show_sql", "true");
      hibernateProperties.put("hibernate.hbm2ddl.auto", "update");

      sessionFactory.setHibernateProperties(hibernateProperties);
      return sessionFactory;
   }

   @Bean
   public HibernateTransactionManager transactionManager() {
      HibernateTransactionManager txManager = new HibernateTransactionManager();
      txManager.setSessionFactory(sessionFactory().getObject());
      return txManager;
   }
}
