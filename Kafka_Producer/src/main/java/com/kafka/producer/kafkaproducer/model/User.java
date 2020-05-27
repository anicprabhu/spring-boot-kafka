package com.kafka.producer.kafkaproducer.model;

public class User {

    private String name;

    private String department;

    private String salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public User(String name, String department, String salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    
}