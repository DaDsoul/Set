package com.akezhanmussa.impl;

/**
 * Created by talgat on 04.03.18.
 */
public class Student implements Comparable<Student>{
    String name;
    int id;

    public Student(String name, int age){
        this.name = name;
        this.id = age;
    }

    public boolean equals(Object obj){
        if (obj instanceof Student){
            Student someStudent = (Student) obj;
            return id == someStudent.id && name.equals(someStudent.name);
        }
        return false;
    }

    public String toString(){
        return "The student is " + name + " his id is " + id;
    }

    @Override
    public int compareTo(Student o) {
        if (id < o.id){
            return -1;
        } else if (id > o.id){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        return 42;//;
    }
}
