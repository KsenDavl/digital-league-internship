package com.example.examproject.service.api;

import com.example.examproject.entity.Student;
import dto.StudentDto;
import dto.TeacherDto;

import java.util.List;

public interface TeacherService {

    List<TeacherDto> getAllTeachers();

    TeacherDto getTeacherById(int id);

    TeacherDto addTeacher(TeacherDto teacher);

    TeacherDto updateTeacher(TeacherDto teacher);

    void deleteTeacherById(int id);

    List<StudentDto> getStudentsByTeacherId(int id);

    void deleteTeacherFromAllStudents(int id);
}
