package com.example.examproject.service.impl;

import com.example.examproject.dao.TeacherMapper;
import com.example.examproject.entity.Student;
import com.example.examproject.entity.Teacher;
import com.example.examproject.grpcclient.GrpcClientService;
import com.example.examproject.jmsservice.JmsProducer;
import com.example.examproject.service.api.TeacherService;
import com.example.examproject.dto.StudentDto;
import com.example.examproject.dto.TeacherDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final String QUEUE_NAME = "exam.topic";

    TeacherMapper teacherMapper;
    ModelMapper modelMapper;
    GrpcClientService grpcClientService;
    private final JmsProducer jmsProducer;

    @Autowired
    public TeacherServiceImpl(TeacherMapper teacherMapper, ModelMapper modelMapper, GrpcClientService grpcClientService, JmsProducer jmsProducer) {
        this.teacherMapper = teacherMapper;
        this.modelMapper = modelMapper;
        this.grpcClientService = grpcClientService;
        this.jmsProducer = jmsProducer;
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> entities = teacherMapper.getAllTeachers();
        grpcClientService.sendMessageToServer("Получен список всех учителей");
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.GET);
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }

    @Override
    public TeacherDto getTeacherById(int id) {
        Teacher teacher = teacherMapper.getTeacherById(id);
        grpcClientService.sendMessageToServer(String.format("Получена сущность учителя по идентификатору = %d", id));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.GET);
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public TeacherDto addTeacher(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacherMapper.addTeacher(teacher);
        grpcClientService.sendMessageToServer(String.format
                ("Добавлена новая сущность учителя с идентификатором = %d", teacher.getTeacherId()));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.POST);
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        teacherMapper.updateTeacher(teacher);
        grpcClientService.sendMessageToServer(String.format
                ("Обновлена сущность учителя с идентификатором = %d", teacher.getTeacherId()));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.PUT);
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public void deleteTeacherById(int id) {
        deleteTeacherFromAllStudents(id);
        grpcClientService.sendMessageToServer(String.format("Удалена сущность учителя с идентификатором = %d", id));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.DELETE);
        teacherMapper.deleteTeacherById(id);
    }

    @Override
    public List<StudentDto> getStudentsByTeacherId(int id) {
        List<Student> entities = teacherMapper.getStudentsByTeacherId(id);
        grpcClientService.sendMessageToServer(String.format
                ("Получен список студентов учителя с идентификатором = %d", id));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.GET);
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }

    @Override
    public void deleteTeacherFromAllStudents(int id) {
        grpcClientService.sendMessageToServer(String.format("Удалены все связи студентов и учителя с идентификатором = %d", id));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.GET);
        teacherMapper.deleteTeacherFromAllStudents(id);
    }


}
