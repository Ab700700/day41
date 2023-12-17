package com.example.schoolmanagement.Contorller;

import com.example.schoolmanagement.Model.Student;
import com.example.schoolmanagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sm/student")
@RequiredArgsConstructor
public class StudentController {

    private  final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudents(){
        return  ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student){
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body("Student added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody @Valid Student student){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id,student));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.deleteStudnet(id));
    }

    @PutMapping("/assign/{stu_id}/{co_id}")
    public ResponseEntity assignStudentToCourse(@PathVariable Integer stu_id, @PathVariable Integer co_id){
        return  ResponseEntity.status(HttpStatus.OK).body(studentService.assignStudentToCourse(stu_id,co_id));
    }

    @PutMapping("/major/change/{stu_id}/{major}")
    public ResponseEntity changeMajor(@PathVariable Integer stu_id, @PathVariable String major){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.changeMajor(stu_id,major));
    }

}
