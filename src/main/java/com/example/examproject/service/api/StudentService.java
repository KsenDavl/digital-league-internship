package com.example.examproject.service.api;

import com.example.examproject.dto.StudentDto;
import com.example.examproject.dto.TeacherDto;

import java.util.List;

public interface StudentService {

    public List<StudentDto> getAllStudents();

    public StudentDto getStudentById(int id);

    public StudentDto addStudent(StudentDto student);

    public StudentDto updateStudent(StudentDto student);

    public void deleteStudent(int id);

    void removeStudentFromTeacher(int studentId, int teacherId);

    void addStudentToTeacher(int studentId, int teacherId);

    List<TeacherDto> getTeachersByStudentId(int id);

    void removeStudentFromAllTeachers(int id);

}
