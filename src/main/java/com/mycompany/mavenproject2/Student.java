/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author youss
 */
public class Student {
    
    public int id = 0;
    public String name = "";
    public int bday = 0;
    public int bmonth = 0;
    public int byear = 0;
    public int genderMale = 1;
    public String nationality = "";
    public String phone = "";
    public String email = "";
    public int enrYear = 0;
    public String major = "";
    public String courses = "";
    public double gpa = 0.0;
    public double attendance = 0.0;
    
    private String[] coursesParsed;
    private int coursesCount=0;
    
    public void parseCourses(){
        //separates courses to individual strings in an array
        coursesParsed = courses.split(",");
        coursesCount = coursesParsed.length;
    }
    
    public int getCoursesCount(){
        return coursesCount;
    }
    
    public String getCourse(int index){
        if(coursesCount> 0 && index < coursesCount && index >= 0){
            return coursesParsed[index];
        }else{
            return "not found";
        }
    }
    
    public boolean good(){
         if (id == 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (bday == 0 || bmonth == 0 || byear == 0) return false;
        if (nationality == null || nationality.isEmpty()) return false;
        if (phone == null || phone.isEmpty()) return false;
        if (email == null || email.isEmpty()) return false;
        if (enrYear == 0) return false;
        if (major == null || major.isEmpty()) return false;
        if (gpa == 0.0) return false;
        if (attendance == 0.0) return false;
        
        return true;

    }
    
    public boolean good4Insert(){
        if (name == null || name.isEmpty()) return false;
        if (bday == 0 || bmonth == 0 || byear == 0) return false;
        if (nationality == null || nationality.isEmpty()) return false;
        if (phone == null || phone.isEmpty()) return false;
        if (email == null || email.isEmpty()) return false;
        if (enrYear == 0) return false;
        if (major == null || major.isEmpty()) return false;
        if (gpa == 0.0) return false;
        if (attendance == 0.0) return false;
        
        return true;

    }
    
    
    
    public void print(){
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Birth Date: " + bday + "/" + bmonth + "/" + byear);
        System.out.println("Gender (Male): " + genderMale);
        System.out.println("Nationality: " + nationality);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Enrollment Year: " + enrYear);
        System.out.println("Major: " + major);
        System.out.println("Courses: " + courses);
        System.out.println("GPA: " + gpa);
        System.out.println("Attendance: " + attendance);

    }
    
    
}
