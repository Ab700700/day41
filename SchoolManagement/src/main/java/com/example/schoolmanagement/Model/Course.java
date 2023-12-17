package com.example.schoolmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Major should not be empty")
    private String major;

    @ManyToOne
    @JoinColumn(name = "teacherid",referencedColumnName = "id")
    @JsonIgnore
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Student> students;

}
