package com.example.examproject.service.impl;

import com.example.examproject.dao.TeacherMapper;
import com.example.examproject.entity.Student;
import com.example.examproject.entity.Teacher;
import com.example.examproject.service.api.TeacherService;
import dto.StudentDto;
import dto.TeacherDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    TeacherMapper teacherMapper;
    ModelMapper modelMapper;

    @Autowired
    public TeacherServiceImpl(TeacherMapper teacherMapper, ModelMapper modelMapper) {
        this.teacherMapper = teacherMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> entities = teacherMapper.getAllTeachers();
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }

    @Override
    public TeacherDto getTeacherById(int id) {
        Teacher teacher = teacherMapper.getTeacherById(id);
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public TeacherDto addTeacher(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacherMapper.addTeacher(teacher);
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacherMapper.updateTeacher(teacher);
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public void deleteTeacherById(int id) {
        deleteTeacherFromAllStudents(id);
        teacherMapper.deleteTeacherById(id);
    }

    @Override
    public List<StudentDto> getStudentsByTeacherId(int id) {
        List<Student> entities = teacherMapper.getStudentsByTeacherId(id);
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }

    @Override
    public void deleteTeacherFromAllStudents(int id) {
        teacherMapper.deleteTeacherFromAllStudents(id);
    }


}
