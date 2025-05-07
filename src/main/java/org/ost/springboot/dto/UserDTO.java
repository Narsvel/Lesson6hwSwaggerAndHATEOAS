package org.ost.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {

    private int id;

    @NotEmpty(message = "Name should not be empty.")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters.")
    private String name;

    @NotEmpty(message = "Email should not be empty.")
    @Email
    private String email;

    @Min(value = 0, message = "Age should be greater than 0.")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
