package com.example.security.controller;

import com.example.security.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students=new ArrayList<>(
            List.of(new Student(1,"prince","java"),
                    new Student(2,"abhihek","c"))
    );

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @PostMapping("/addStudent")
    public void addStduent(@RequestBody Student student){
        students.add(student);
    }


}
