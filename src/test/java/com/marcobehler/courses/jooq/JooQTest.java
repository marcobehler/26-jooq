package com.marcobehler.courses.jooq;

import com.marcobehler.course.service.CourseService;
import com.marcobehler.courses.jooq.public_.tables.records.CoursesRecord;
import org.junit.Test;

public class JooQTest {

    @Test
    public void name() {
        CoursesRecord record = new CourseService().save("Hibernate rockz", "Hibernate is so much better!", 5000);
        System.out.println("record = " + record);
    }
}