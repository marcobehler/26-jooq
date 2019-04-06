package com.marcobehler.courses.jooq;

import com.marcobehler.courses.jooq.public_.tables.Purchases;
import com.marcobehler.courses.jooq.public_.tables.Users;
import com.marcobehler.courses.jooq.public_.tables.daos.CoursesDao;
import com.marcobehler.courses.jooq.public_.tables.pojos.Courses;
import com.marcobehler.courses.jooq.public_.tables.records.PurchasesRecord;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;

import static com.marcobehler.courses.jooq.information_schema.tables.Users.USERS;
import static org.assertj.core.api.Assertions.assertThat;

public class JooQTest {

    private DataSource ds;

    private Configuration jooqConfig;

    @Before
    public void setUp() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:~/database");
        config.setUsername("");
        config.setPassword("");
        ds = new HikariDataSource(config);

        jooqConfig = new DefaultConfiguration().set(ds).set(SQLDialect.H2);
    }

    @Test
    public void saving_our_first_pojo() {
        Courses c = new Courses();

        c.setTitle("Hibernate rockz");
        c.setDescription("Hibernate is so much better!");
        c.setPrice((short) 5000);

        CoursesDao dao = new CoursesDao(jooqConfig);
        dao.insert(c);
        assertThat(c.getId()).isNotNull();

        Courses course = dao.findById(c.getId());
        assertThat(course).isNotNull();
        assertThat(course.getTitle()).isEqualTo(c.getTitle());
    }

    @Test
    public void our_first_join() {
        DSLContext create = DSL.using(jooqConfig);

        create.select().from(Users.USERS)
                .leftOuterJoin(Purchases.PURCHASES)
                .onKey()
                .fetch();

    }
}