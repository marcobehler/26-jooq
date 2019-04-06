package com.marcobehler.course.service;

import com.marcobehler.courses.jooq.public_.tables.Courses;
import com.marcobehler.courses.jooq.public_.tables.records.CoursesRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CourseService {


    public CoursesRecord findById(Integer id) {
        //
        return null;

    }

    public CoursesRecord save(String title, String description, Integer price) {
        try (Connection connection = getConnection()) {
            DSLContext create = DSL.using(connection, SQLDialect.H2);
            CoursesRecord coursesRecord = create.newRecord(Courses.COURSES);
            coursesRecord.setTitle(title);
            coursesRecord.setDescription(description);
            coursesRecord.setPrice(price.shortValue());
            coursesRecord.store();
            return coursesRecord;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/database", "", "");
    }

}
