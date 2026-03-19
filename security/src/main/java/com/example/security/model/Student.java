package com.example.security.model;

public class Student {
    private int rollno;
    private String name;
    private String tech;

    public Student(int rollno, String name, String tech) {
        this.rollno=rollno;
        this.name=name;
        this.tech=tech;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollno=" + rollno +
                ", name='" + name + '\'' +
                ", tech='" + tech + '\'' +
                '}';
    }
}
