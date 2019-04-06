package com.marcobehler.courses.jooq;

import com.marcobehler.MySpringContext;
import com.marcobehler.courses.jooq.public_.tables.Purchases;
import com.marcobehler.courses.jooq.public_.tables.Users;
import com.marcobehler.courses.jooq.public_.tables.daos.CoursesDao;
import com.marcobehler.courses.jooq.public_.tables.pojos.Courses;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MySpringContext.class)
public class JooQTest {

    @Autowired
    private CoursesDao dao;

    @Autowired
    private DSLContext create;


    @Test
    public void saving_our_first_pojo() {
        Courses c = new Courses();

        c.setTitle("Hibernate rockz");
        c.setDescription("Hibernate is so much better!");
        c.setPrice((short) 5000);


        dao.insert(c);
        assertThat(c.getId()).isNotNull();

        Courses course = dao.findById(c.getId());
        assertThat(course).isNotNull();
        assertThat(course.getTitle()).isEqualTo(c.getTitle());
    }

    @Test
    public void our_first_join() {
        create.select().from(Users.USERS)
                .leftOuterJoin(Purchases.PURCHASES)
                .onKey()
                .fetch();

    }
}