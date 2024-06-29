package com.example.bel1.B3;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class SudentController {
    private List<Student> students = new ArrayList<>();

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        student.setId((long) (students.size() + 1));
        students.add(student);
        return student;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id){
        Optional<Student> student = students.stream().filter(s -> s.getId().equals(id)).findFirst();
        return student.orElse(null);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable long id, @RequestBody Student student){
        Optional<Student> optionalStudent = students.stream().filter(s -> s.getId().equals(id)).findFirst();
        if (optionalStudent.isPresent()) {
            Student studentUpdate = optionalStudent.get();
            studentUpdate.setName(student.getName());
            studentUpdate.setAge(student.getAge());
            studentUpdate.setEmail(student.getEmail());
            return studentUpdate;
        }else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteSudent(@PathVariable long id){
        Optional<Student> studentOptional = students.stream().filter(s -> s.getId().equals(id)).findFirst();
        if (studentOptional.isPresent()){
            students.remove(studentOptional.get());
            return "Deleted";
        }else {
            return "Not Found";
        }
    }
}
