package com.example.examproject.service.impl;

import com.example.examproject.dao.StudentMapper;
import com.example.examproject.entity.Student;
import com.example.examproject.entity.Teacher;
import com.example.examproject.service.api.StudentService;
import dto.StudentDto;
import dto.TeacherDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    StudentMapper studentMapper;
    ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, ModelMapper modelMapper) {
        this.studentMapper = studentMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> entities = studentMapper.getAllStudents();
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }

    @Override
    public StudentDto getStudentById(int id) {
        Student student = studentMapper.getStudentById(id);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        studentMapper.addStudent(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        studentMapper.updateStudent(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudent(int id) {
        removeStudentFromAllTeachers(id);
        studentMapper.deleteStudent(id);
    }

    @Override
    public void removeStudentFromTeacher(int studentId, int teacherId) {
        studentMapper.removeStudentFromTeacher(studentId, teacherId);
    }

    @Override
    public void addStudentToTeacher(int studentId, int teacherId) {
        studentMapper.addStudentToTeacher(studentId, teacherId);
    }

    @Override
    public List<TeacherDto> getTeachersByStudentId(int id) {
        List<Teacher> entities = studentMapper.getTeachersByStudentId(id);
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }

    @Override
    public void removeStudentFromAllTeachers(int id) {
        studentMapper.removeStudentFromAllTeachers(id);
    }


}
