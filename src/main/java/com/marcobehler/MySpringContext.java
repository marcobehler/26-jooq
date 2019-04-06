package com.marcobehler;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@ComponentScan
public class MySpringContext {

    @Bean
    public DSLContext dslContext() {
        return DSL.using(configuration());
    }


    @Bean
    public DataSource datasource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:~/database");
        config.setUsername("");
        config.setPassword("");
        return new HikariDataSource(config);
    }

    @Bean
    public Configuration configuration() {
        return new DefaultConfiguration().set(datasource()).set(SQLDialect.H2);
    }
}
