package com.marcobehler.courses.jooq;

import com.marcobehler.courses.jooq.public_.tables.Courses;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JooQTest {

    @Test
    public void name() {
        String userName = "";
        String password = "";
        String url = "jdbc:h2:~/database";

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {

            DSLContext create = DSL.using(conn, SQLDialect.H2);
            Result<Record> result = create.select().from(Courses.COURSES).fetch();
            for (Record r : result) {
                Integer id = r.getValue(Courses.COURSES.ID);
                String title = r.getValue(Courses.COURSES.TITLE);
                String description = r.getValue(Courses.COURSES.DESCRIPTION);
                Short price = r.getValue(Courses.COURSES.PRICE);
                System.out.println("id = " + id + " title= " + title + " description= " + description + " price=" + price);
            }
            // ...
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}