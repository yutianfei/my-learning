package com.wsy.rxdemo.lambda;

import lombok.Data;

/**
 * Description
 * 2016/5/27.
 */
@Data
public class Person {
    private String firstName, lastName, job, gender;
    private int salary, age;

    public Person() {
    }

    public Person(String firstName, String lastName, String job, String gender, int age, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }
}
