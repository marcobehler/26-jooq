package com.marcobehler.courses.jooq;

import com.marcobehler.course.service.CourseDao;
import com.marcobehler.courses.jooq.public_.tables.pojos.Courses;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JooQTest {

    @Test
    public void saving_our_first_pojo() {
        Courses c = new Courses();

        c.setTitle("Hibernate rockz");
        c.setDescription("Hibernate is so much better!");
        c.setPrice((short) 5000);

        Courses pojo = new CourseDao().save(c);
        assertThat(pojo.getId()).isNotNull();
    }
}