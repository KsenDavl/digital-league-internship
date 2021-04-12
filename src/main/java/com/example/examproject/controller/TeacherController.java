package com.example.examproject.controller;

import com.example.examproject.exceptionhandling.TeacherNotFoundDto;
import com.example.examproject.exceptionhandling.TeacherNotFoundException;
import com.example.examproject.service.api.TeacherService;
import com.example.examproject.dto.StudentDto;
import com.example.examproject.dto.TeacherDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Учитель", description = "API для учителей")
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    @Operation(summary = "Получение данных всех учителей")
    public List<TeacherDto> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных учителя по его идентификатору")
    public TeacherDto getTeacherById(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    @Operation(summary = "Добавление нового учителя")
    public TeacherDto addTeacher(@RequestBody TeacherDto teacher) {
        return teacherService.addTeacher(teacher);
    }

    @PutMapping
    @Operation(summary = "Обновление данных учителя")
    public TeacherDto updateTeacher(@RequestBody TeacherDto teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление учителя по его идентификатору")
    public void deleteTeacherById(@PathVariable int id) {
        teacherService.deleteTeacherById(id);
    }

    @GetMapping("/{id}/students")
    @Operation(summary = "Получение всех студентов учителя по идентификатору учителя")
    public List<StudentDto> getStudentsByTeacherId(@PathVariable int id) {
        return teacherService.getStudentsByTeacherId(id);
    }

    @ExceptionHandler
    public ResponseEntity<TeacherNotFoundDto> handleException (TeacherNotFoundException exception) {
        TeacherNotFoundDto dto = new TeacherNotFoundDto();
        dto.setInfo(exception.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
