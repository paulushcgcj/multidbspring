package org.paulushc.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("file:./database.properties")
public class DatabaseThird {

    @Value("${third.db.driver}")
    private String driver;
    @Value("${third.db.url}")
    private String url;
    @Value("${third.db.username}")
    private String username;
    @Value("${third.db.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.show_sql}")
    private boolean showSQL;
    @Value("${hibernate.format_sql}")
    private boolean formatSQL;
    @Value("${entitymanager.packages.to.scan}")
    private String packageScan;
    @Value("${connection.release_mode}")
    private String releaseMode;

    @Bean(name = "thirdDataSource")
    public DataSource thirdDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "thirdEntityManager")
    public LocalContainerEntityManagerFactoryBean thirdEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(thirdDataSource());
        em.setPackagesToScan(new String[] { packageScan });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    @Bean(name = "thirdTransactionManager")
    public PlatformTransactionManager thirdTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(thirdEntityManager().getObject());
        return transactionManager;
    }

    @Bean(name = "thirdSessionFactory")
    public LocalSessionFactoryBean thirdSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(thirdDataSource());
        sessionFactoryBean.setPackagesToScan(packageScan);
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", false);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        properties.put("hibernate.show_sql",showSQL);
        properties.put("hibernate.format_sql",formatSQL);
        properties.put("entitymanager.packages.to.scan",packageScan);
        properties.put("connection.release_mode",releaseMode);
        return properties;
    }
}
