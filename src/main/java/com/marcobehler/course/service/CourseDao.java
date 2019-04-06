package com.marcobehler.course.service;

import com.marcobehler.courses.jooq.public_.tables.daos.CoursesDao;
import com.marcobehler.courses.jooq.public_.tables.pojos.Courses;
import com.marcobehler.courses.jooq.public_.tables.records.CoursesRecord;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CourseDao {

    public CoursesRecord findById(Integer id) {
        //
        return null;

    }

    public Courses save(Courses courses) {
        try (Connection connection = getConnection()) {
            CoursesDao coursesDao = new CoursesDao(new DefaultConfiguration().set(connection).set(SQLDialect.H2));
            coursesDao.insert(courses);
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/database", "", "");
    }

}
