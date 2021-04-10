package com.example.examproject.controller;

import com.example.examproject.service.api.StudentService;
import com.example.examproject.dto.StudentDto;
import com.example.examproject.dto.TeacherDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Студент", description = "API для студентов")
@RestController
@RequestMapping("/students")
public class StudentController {

    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Operation(summary = "Получение данных всех студентов")
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных студента по его идентификатору")
    public StudentDto getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    @Operation(summary = "Добавление нового студента")
    public StudentDto addStudent(@RequestBody StudentDto studentDto) {
        return studentService.addStudent(studentDto);
    }

    @PutMapping
    @Operation(summary = "Обновление данных студента")
    public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление студента по его идентификатору")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    @DeleteMapping("/{studentId}/teacher/{teacherId}")
    @Operation(summary = "Отписание студента от учителя")
    public void removeStudentFromTeacher(@PathVariable int studentId, @PathVariable int teacherId) {
        studentService.removeStudentFromTeacher(studentId, teacherId);
    }

    @PostMapping("/{studentId}/teacher/{teacherId}")
    @Operation(summary = "Приписание студента учителю")
    public void addStudentToTeacher(@PathVariable int studentId, @PathVariable int teacherId) {
        studentService.addStudentToTeacher(studentId, teacherId);
    }

    @GetMapping("/get_teachers_by/{id}")
    @Operation(summary = "Получение всех учителей студента по идентификатору студента")
    public List<TeacherDto> getTeachersByStudentId(@PathVariable int id) {
        return studentService.getTeachersByStudentId(id);
    }
}
