package com.marcobehler;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@ComponentScan
@EnableTransactionManagement
public class MySpringContext {

    @Bean
    public DSLContext dslContext() {
        return DSL.using(configuration());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(datasource());
    }


    @Bean
    public DataSourceConnectionProvider dataSourceConnectionProvider() {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(datasource()));
    }

    @Bean
    public TransactionProvider transactionProvider(PlatformTransactionManager platformTransactionManager) {
        return new TransactionProvider() {
            @Override
            public void begin(TransactionContext ctx) throws DataAccessException {
                TransactionDefinition definition = new DefaultTransactionDefinition(
                        TransactionDefinition.PROPAGATION_NESTED);
                TransactionStatus status = platformTransactionManager.getTransaction(definition);
                ctx.transaction(new SpringTransaction(status));
            }

            @Override
            public void commit(TransactionContext ctx) throws DataAccessException {
                platformTransactionManager.commit(getTransactionStatus(ctx));
            }

            @Override
            public void rollback(TransactionContext ctx) throws DataAccessException {
                platformTransactionManager.rollback(getTransactionStatus(ctx));
            }

            private TransactionStatus getTransactionStatus(TransactionContext ctx) {
                SpringTransaction transaction = (SpringTransaction) ctx.transaction();
                return transaction.getTxStatus();
            }
        };
    }

    public static class SpringTransaction implements Transaction {

        private final TransactionStatus transactionStatus;

        SpringTransaction(TransactionStatus transactionStatus) {
            this.transactionStatus = transactionStatus;
        }

        public TransactionStatus getTxStatus() {
            return this.transactionStatus;
        }

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
        return new DefaultConfiguration().set(dataSourceConnectionProvider()
        ).set(SQLDialect.H2);
    }
}
