package com.example.examproject.service.impl;

import com.example.examproject.dao.TeacherMapper;
import com.example.examproject.entity.Student;
import com.example.examproject.entity.Teacher;
import com.example.examproject.grpcclient.GrpcClientService;
import com.example.examproject.service.api.TeacherService;
import com.example.examproject.dto.StudentDto;
import com.example.examproject.dto.TeacherDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    TeacherMapper teacherMapper;
    ModelMapper modelMapper;
    GrpcClientService grpcClientService;

    @Autowired
    public TeacherServiceImpl(TeacherMapper teacherMapper, ModelMapper modelMapper, GrpcClientService grpcClientService) {
        this.teacherMapper = teacherMapper;
        this.modelMapper = modelMapper;
        this.grpcClientService = grpcClientService;
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> entities = teacherMapper.getAllTeachers();
        grpcClientService.sendMessageToServer("Получен список всех учителей");
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }

    @Override
    public TeacherDto getTeacherById(int id) {
        Teacher teacher = teacherMapper.getTeacherById(id);
        grpcClientService.sendMessageToServer(String.format("Получена сущность учителя по идентификатору = %d", id));
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public TeacherDto addTeacher(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacherMapper.addTeacher(teacher);
        grpcClientService.sendMessageToServer(String.format
                ("Добавлена новая сущность учителя с идентификатором = %d", teacher.getTeacherId()));
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacherMapper.updateTeacher(teacher);
        grpcClientService.sendMessageToServer(String.format
                ("Обновлена сущность учителя с идентификатором = %d", teacher.getTeacherId()));
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public void deleteTeacherById(int id) {
        deleteTeacherFromAllStudents(id);
        grpcClientService.sendMessageToServer(String.format("Удалена сущность учителя с идентификатором = %d", id));
        teacherMapper.deleteTeacherById(id);
    }

    @Override
    public List<StudentDto> getStudentsByTeacherId(int id) {
        List<Student> entities = teacherMapper.getStudentsByTeacherId(id);
        grpcClientService.sendMessageToServer(String.format
                ("Получен список студентов учителя с идентификатором = %d", id));
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }

    @Override
    public void deleteTeacherFromAllStudents(int id) {
        grpcClientService.sendMessageToServer(String.format("Удалены все связи студентов и учителя с идентификатором = %d", id));
        teacherMapper.deleteTeacherFromAllStudents(id);
    }


}
