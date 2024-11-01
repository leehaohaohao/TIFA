package com.tifa.common.config;

import com.tifa.common.entity.dto.AuditDto;
import com.tifa.common.handler.mybatis.EnumTypeHandlerFactory;
import com.tifa.common.handler.mybatis.JsonArrayTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * mybatis 配置类
 *
 * @author lihao
 * @date  2024/11/1--13:03
 * @since 1.0
 */
@Configuration
@MapperScan("com.tifa.common.mapper")
public class MybatisConfig {
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;

    @Value("${mybatis.configuration.map-underscore-to-camel-case}")
    private boolean mapUnderscoreToCamelCase;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 设置 MyBatis 配置
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(mapUnderscoreToCamelCase);
        sessionFactoryBean.setConfiguration(configuration);
        // 获取所有的枚举类型处理器
        TypeHandlerRegistry typeHandlerRegistry = sessionFactoryBean.getObject().getConfiguration().getTypeHandlerRegistry();
        // 注册枚举类型处理器
        EnumTypeHandlerFactory.registerEnumTypeHandlers(typeHandlerRegistry, new Class[]{
                AuditDto.AuditType.class,
                AuditDto.AuditStatus.class
        });
        /*typeHandlerRegistry.register(java.util.List.class, JsonArrayTypeHandler.class);*/
        return sessionFactoryBean.getObject();
    }
}
