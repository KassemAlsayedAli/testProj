package com.kass.dao;


import com.com.kass.domain.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kass on 2017-03-10.
 */
public class PersonDao {

    static boolean isInit = false;
    Connection conn;
    Statement stmt;

    public PersonDao() {
        init();
    }

    private void init(){

        if (isInit)
            return;

        isInit = true;

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            connectionToDerby();
            stmt = conn.createStatement();

            // drop table
            //stmt.executeUpdate("Drop Table users");

            // create table
            stmt.executeUpdate("Create table users (id int primary key, name varchar(30), age varchar(5), sex varchar(1))");

            // insert 2 rows
            stmt.executeUpdate("insert into users values (1,'tom', '15', 'M')");
            stmt.executeUpdate("insert into users values (2,'peter', '15', 'M')");

            getPersons(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        PersonDao app = new PersonDao();

    }


    public void connectionToDerby() throws SQLException {
        // -------------------------------------------
        // URL format is
        // jdbc:derby:<local directory to save data>
        // -------------------------------------------

        // file db
        //String dbUrl = "jdbc:derby:c:\\Users\\shengw\\MyDB\\demo;create=true";

        String dbUrl = "jdbc:derby:memory:demo;create=true";

        conn = DriverManager.getConnection(dbUrl);
    }

    public List<Person> getPersons(String name) throws SQLException {


        // query
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        List<Person> retL = new ArrayList<Person>();

        // print out query result
        while (rs.next()) {
            //System.out.printf("%d\t%s\t%s\t%s\n", rs.getInt("id"), rs.getString("name"),rs.getString("age"),rs.getString("sex"));

            Person p = new Person(rs.getString("name"),rs.getString("age"),rs.getString("sex"));

           if (name == null || "ALL".compareToIgnoreCase(name) ==0 || name.compareToIgnoreCase(rs.getString("name")) == 0) {
               retL.add(p);
           }
        }

        System.out.println(retL);
        return retL;
    }
}

