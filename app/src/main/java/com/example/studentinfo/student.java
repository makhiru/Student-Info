package com.example.studentinfo;

public class student {

    int imgstudent;
    String name, fname, rollno, standard;
    int id;

    public student(String name, String fname, String rollno, String standard, int id) {
        this.name = name;
        this.fname = fname;
        this.rollno = rollno;
        this.standard = standard;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    student(String name, String fname, String rollno, String standard) {
        this.name = name;
        this.fname = fname;
        this.rollno = rollno;
        this.standard = standard;
    }

}
