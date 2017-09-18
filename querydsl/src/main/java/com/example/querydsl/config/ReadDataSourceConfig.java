package com.example.querydsl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author admin
 * @since 2017/9/18 16:23
 */
@Configuration
@EnableJpaRepositories(value = "com.example.querydsl.repository",
        entityManagerFactoryRef = "readEntityManagerFactory",
        transactionManagerRef = "readTransactionManager")
public class ReadDataSourceConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("readDruidDataSource")
    private DataSource readDruidDataSource;

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     *
     * @return
     */
    @Bean(name = "readEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(readDruidDataSource)
                .properties(getVendorProperties(readDruidDataSource))
                .packages("com.example.querydsl.domain") //设置实体类所在位置
                .persistenceUnit("readPersistenceUnit")//持久化单元名称
                .build();
    }

    /**
     * @param builder
     * @return
     */
    @Bean(name = "readEntityManagerFactory")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return this.readEntityManagerFactory(builder).getObject().createEntityManager();
    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean(name = "readTransactionManager")
    public PlatformTransactionManager readTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(readEntityManagerFactory(builder).getObject());
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }
}
