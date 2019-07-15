package aegs;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    Connection con;
    Statement st;
    PreparedStatement ps;
    
    public Database()
    {
        try {
            loadDriver();
            connectToDb();
            createDb();
            createTableCourse();
            createTableStudent();
            createTableQuestion();
            createTableKeyword();
            createTableScore();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadDriver() throws ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
    }
    private void connectToDb() throws SQLException
    {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "");
        System.out.println("Driver Loaded");
    }
    private void createDb() throws SQLException
    {
        String query = "CREATE DATABASE IF NOT EXISTS AEGS";
        ps = con.prepareStatement(query);
        int a = ps.executeUpdate();
        if(a != -1)
            System.out.println("Db created successfully");
    }
    private void createTableKeyword() throws SQLException
    {
        String query = "CREATE TABLE IF NOT EXISTS keyword(" +
                        "question_id varchar(10) not null, " +
                        "keyword text NOT NULL," +
                        "FOREIGN KEY (`question_id`) REFERENCES `AEGS`.`question`(`question_id`) ON DELETE CASCADE ON UPDATE CASCADE" +
                        ") ENGINE = InnoDB";
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AEGS", "root", "");
        ps = con.prepareStatement(query);
        int a = ps.executeUpdate();
        if(a != -1)
            System.out.println("Keyword created successfully");
    }
    private void createTableQuestion() throws SQLException
    {
        String query = "CREATE TABLE IF NOT EXISTS question(" +
                        "course_id varchar(6) not null, " +
                        "question_id varchar(10) not null, " +
                        "question text not null, " +
                        "question_mark int not null, " +
                        "FOREIGN KEY (`course_id`) REFERENCES `AEGS`.`course`(`course_id`) ON DELETE CASCADE ON UPDATE CASCADE, " +
                        "PRIMARY KEY(question_id)) ENGINE = InnoDB";
        
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AEGS", "root", "");
        ps = con.prepareStatement(query);
        int a = ps.executeUpdate();
        if(a != -1)
            System.out.println("Question created successfully");
    }
    private void createTableScore() throws SQLException
    {
        String query = "CREATE TABLE IF NOT EXISTS score(" +
                        "matric varchar(6) not null, " + 
                        "course_id varchar(6) not null, " + 
                        "grade int not null, " + 
                        "time varchar(30), " +
                        "FOREIGN KEY (`course_id`) REFERENCES `AEGS`.`course`(`course_id`) ON DELETE CASCADE ON UPDATE CASCADE, " +
                        "FOREIGN KEY (`matric`) REFERENCES `AEGS`.`student`(`matric`) ON DELETE CASCADE ON UPDATE CASCADE" +
                        ") ENGINE = InnoDB";
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AEGS", "root", "");
        ps = con.prepareStatement(query);
        int a = ps.executeUpdate();
        if(a != -1)
            System.out.println("Score created successfully");
    }
    private void createTableStudent() throws SQLException
    {
        String query = "CREATE TABLE IF NOT EXISTS student(" +
                        "matric varchar(6) not null, " +
                        "fullname varchar(50), " +
                        "password varchar(20) not null, "+
                        "dept varchar(20), " +
                        "gender varchar(10), " +
                        "mobile varchar(20), " +
                        "email varchar(50), " +
                        "country varchar(50), " +
                        "state varchar(50), " +
                        "image varchar(100), " +
                        "PRIMARY KEY(matric)) ENGINE = InnoDB";
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AEGS", "root", "");
        ps = con.prepareStatement(query);
        int a = ps.executeUpdate();
        if(a != -1)
            System.out.println("Student created successfully");
    }
    private void createTableCourse() throws SQLException
    {
        String query = "CREATE TABLE IF NOT EXISTS course(" +
                        "course_id varchar(6) not null, " +
                        "course_title text not null, " +
                        "exam_time int not null, " +
                        "no_of_question int not null ," +
                        "PRIMARY KEY(course_id)) ENGINE = InnoDB";
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AEGS", "root", "");
        ps = con.prepareStatement(query);
        int a = ps.executeUpdate();
        if(a != -1)
            System.out.println("Course created successfully");
    }
    public static void main(String [] arg)
    {
        new Database();
    }
}
