package com.example.schoolmanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotNull(message = "Age should not be empty")
    @Positive(message = "Age should be positive number")
    @Min(value = 16,message = "Age should at least be 16")
    private Integer age;
    @NotEmpty(message = "Major should not empty")
    private String major;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}
