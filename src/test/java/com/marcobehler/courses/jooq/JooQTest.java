package com.marcobehler.courses.jooq;

import com.marcobehler.course.service.CourseService;
import com.marcobehler.courses.jooq.public_.tables.records.CoursesRecord;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JooQTest {

    @Test
    public void saving_our_first_record() {
        CoursesRecord record = new CourseService().save("Hibernate rockz", "Hibernate is so much better!", 5000);
        assertThat(record.getId()).isNotNull();
    }
}