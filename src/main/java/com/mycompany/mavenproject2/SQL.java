/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author youssef
 */
public class SQL {
    private Connection c = null;
    private Statement stmt = null;
    private boolean tableCreated = false;
     
    public boolean connect(String databaseName){//connects to student database
        try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:"+ databaseName + ".db");
            return (c != null);
      } catch ( ClassNotFoundException | SQLException e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         return false;
      }
    }
    
    public SQL(){
        try {
            connect("student");
            stmt = c.createStatement();
                

        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
    public boolean tableThere(){
        try{
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + "student" + "';";

        ResultSet rs;
            rs = stmt.executeQuery(sql);
            tableCreated = rs.next();
            return tableCreated;
                 
        }catch(SQLException err){
             System.out.println("An error occurred: " + err.getMessage());
             return false;
        } 
    }
    
    public void CreateStudentTable(){
        String sql = "CREATE TABLE student " +
                        "(id                INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " name              TEXT        NOT NULL, " + 
                        " bday              INTEGER     NOT NULL, " +
                        " bmonth            INTEGER     NOT NULL, " +
                        " byear             INTEGER     NOT NULL, " +
                        " genderMale        INTEGER     NOT NULL, " +
                        " nationality       CHAR(50)    NOT NULL, " + 
                        " phone             CHAR(50)    NOT NULL, " + 
                        " email             TEXT        NOT NULL, " + 
                        " enrYear           INTEGER     NOT NULL, " +
                        " major             CHAR(50)    NOT NULL, " +
                        " courses           TEXT                , " +
                        " gpa               REAL        NOT NULL, " +
                        " attendance        REAL        NOT NULL) " ; 
        
        try{
            stmt.executeUpdate(sql);
            tableCreated = true;
        }catch(SQLException e){
            System.err.println("create table : "+ e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    public void insertStudent(Student student){
        
                if(true){
                    
                    String sql = "insert into student(name, bday, bmonth, byear, genderMale, nationality, phone, "
                   + "email, enrYear, major, courses, gpa, attendance)   "
                   + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try{
                PreparedStatement pstmt = c.prepareStatement(sql);
                
                // Set parameters for the prepared statement
                pstmt.setString(1, student.name);
                pstmt.setInt(2, student.bday);
                pstmt.setInt(3, student.bmonth);
                pstmt.setInt(4, student.byear);
                pstmt.setInt(5, student.genderMale);
                pstmt.setString(6, student.nationality);
                pstmt.setString(7, student.phone);
                pstmt.setString(8, student.email);
                pstmt.setInt(9, student.enrYear);
                pstmt.setString(10, student.major);
                pstmt.setString(11, student.courses);
                pstmt.setDouble(12, student.gpa);
                pstmt.setDouble(13, student.attendance);
                
                System.out.println("template student finished");

                // Execute the update
                pstmt.executeUpdate();
                System.out.println("inserted student");
                pstmt.close();
            }catch (Exception e) {
                System.out.println("Error inserting : " + e);
            }
                    
                    
                    
                    /*
                    stmt.executeUpdate(
                    "insert into student(name, bday, bmonth, byear, genderMale, nationality, phone,"
                    +"email, enrYear, major, courses, gpa, attendance)  "
                    +"values('"+s.name+"', " +s.bday + ", "+s.bmonth+", "+s.byear
                    +", "+s.genderMale
                            +", '"+ s.nationality+"', '" + s.phone+"', '"+
                     s.email+"', "+ s.enrYear+", '" +s.major+"', '" 
                    + s.courses+"', "+s.gpa +", "+ s.attendance +")"
                    );
            
                }
                } catch (SQLException ex) {
                    Logger.getLogger(SQL.class.getName()).log(Level.ALL, null, ex);}*/
        }
    }   
    /**
     *
     * @param id
     * @return returns student object containing necessary data
     */
    public Student getStudent(int id){
        if(tableCreated){
            try {
                ResultSet rs = stmt.executeQuery("select * from student WHERE id =" + Integer.toString(id));
                Student stdnt = new Student();
                if(rs.next()){
                    stdnt.id = rs.getInt("id");
                    stdnt.name = rs.getString("name");
                    stdnt.bday = rs.getInt("bday");
                    stdnt.bmonth = rs.getInt("bmonth");
                    stdnt.byear = rs.getInt("byear");
                    stdnt.genderMale = rs.getInt("genderMale");
                    stdnt.nationality = rs.getString("nationality");
                    stdnt.phone = rs.getString("phone");
                    stdnt.email = rs.getString("email");
                    stdnt.enrYear = rs.getInt("enrYear");
                    stdnt.major = rs.getString("major");
                    stdnt.courses = rs.getString("courses");
                    stdnt.gpa = rs.getDouble("gpa");
                    stdnt.attendance = rs.getDouble("attendance");
                    
                    stdnt.print();
                    return stdnt;
                }
            } catch (SQLException ex) {
                Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Student();
    }
    
    public void updateStudent(Student student) throws SQLException{
        Student test = getStudent(student.id);
        if(test.id == 0){
            //student is not in database
            insertStudent(student);
        }
        else{//student is in database
             String sql = "UPDATE student "
                   + "SET name = ?, bday = ?, bmonth = ?, byear = ?, genderMale = ?, nationality = ?, phone = ?, email = ?, enrYear = ?, major = ?, courses = ?, gpa = ?, attendance = ? "
                   + "WHERE id = ?;";

            try{
                PreparedStatement pstmt = c.prepareStatement(sql);
                // Set parameters for the prepared statement
                pstmt.setString(1, student.name);
                pstmt.setInt(2, student.bday);
                pstmt.setInt(3, student.bmonth);
                pstmt.setInt(4, student.byear);
                pstmt.setInt(5, student.genderMale);
                pstmt.setString(6, student.nationality);
                pstmt.setString(7, student.phone);
                pstmt.setString(8, student.email);
                pstmt.setInt(9, student.enrYear);
                pstmt.setString(10, student.major);
                pstmt.setString(11, student.courses);
                pstmt.setDouble(12, student.gpa);
                pstmt.setDouble(13, student.attendance);
                pstmt.setInt(14, student.id); // The ID for the WHERE clause

                // Execute the update
                int rowsAffected = pstmt.executeUpdate();
                System.out.println(rowsAffected + " row(s) updated.");
            }catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
    }
    
    public void deleteStudent(int id){
        
        
        try {
            stmt.executeUpdate("DELETE from student WHERE id="+ Integer.toString(id));
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.ALL, null, ex);
        }
    }
    
    public int getFirstId(){
        // SQL query to get the first ID
        String sql = "SELECT id FROM student ORDER BY id ASC LIMIT 1";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
               int firstId = rs.getInt("id");
               return firstId;
            }
            else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
 
    }
    
    public int getLastId(){
        // SQL query to get the first ID
        String sql = "SELECT id FROM student ORDER BY id DESC LIMIT 1";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
               int firstId = rs.getInt("id");
               return firstId;
            }
            else{
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
 
    }
    
    
}
