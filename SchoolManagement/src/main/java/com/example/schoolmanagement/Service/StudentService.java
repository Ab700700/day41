package com.example.schoolmanagement.Service;

import com.example.schoolmanagement.Api.ApiException;
import com.example.schoolmanagement.Model.Course;
import com.example.schoolmanagement.Model.Student;
import com.example.schoolmanagement.Repository.CourseRepository;
import com.example.schoolmanagement.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private  final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }

    public String updateStudent(Integer id, Student student){
        Student student1 = studentRepository.findStudentById(id);
        if(student1 == null) throw  new ApiException("Student not found");
        student.setId(id);
        studentRepository.save(student);
        return student.getName()+" Updated";
    }

    public String deleteStudnet(Integer id ){
        Student student = studentRepository.findStudentById(id);
        if(student == null) throw  new ApiException("Student not found");
        studentRepository.delete(student);
        return student.getName()+" deleted";
    }

    public String assignStudentToCourse(Integer stu_id,Integer co_id){
        Student student = studentRepository.findStudentById(stu_id);
        Course course = courseRepository.findCourseById(co_id);
        if(course == null || student == null) throw  new ApiException("Student or course not found");
        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
        courseRepository.save(course);
        return "Student: "+student.getName()+" assigned to Course: "+course.getName() ;
    }

    public String changeMajor(Integer stu_id,String major){
        Student student = studentRepository.findStudentById(stu_id);
        if(student == null) throw new ApiException("Student not found");
        student.setMajor(major);
        for(Course c : student.getCourses()){
            c.getStudents().remove(student);
            courseRepository.save(c);
        }
        studentRepository.save(student);
        return "Student major changed";
    }


}
