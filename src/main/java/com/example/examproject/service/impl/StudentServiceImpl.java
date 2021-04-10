package com.example.examproject.service.impl;

import com.example.examproject.dao.StudentMapper;
import com.example.examproject.dto.StudentDto;
import com.example.examproject.dto.TeacherDto;
import com.example.examproject.entity.Student;
import com.example.examproject.entity.Teacher;
import com.example.examproject.grpcclient.GrpcClientService;
import com.example.examproject.jmsservice.JmsProducer;
import com.example.examproject.service.api.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static final String QUEUE_NAME = "exam.topic";

    private final StudentMapper studentMapper;
    private final ModelMapper modelMapper;
    private final GrpcClientService grpcClientService;
    private final JmsProducer jmsProducer;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, ModelMapper modelMapper, GrpcClientService grpcClientService, JmsProducer jmsProducer) {
        this.studentMapper = studentMapper;
        this.modelMapper = modelMapper;
        this.grpcClientService = grpcClientService;
        this.jmsProducer = jmsProducer;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> entities = studentMapper.getAllStudents();
        grpcClientService.sendMessageToServer("Получен список всех студентов");
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.GET);
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }

    @Override
    public StudentDto getStudentById(int id) {
        Student student = studentMapper.getStudentById(id);
        grpcClientService.sendMessageToServer(String.format("Получена сущность студента по идентификатору = %d", id));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.GET);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        studentMapper.addStudent(student);
        grpcClientService.sendMessageToServer(String.format
                ("Добавлена новая сущность студента с идентификатором = %s", student.getStudentId()));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.POST);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        studentMapper.updateStudent(student);
        grpcClientService.sendMessageToServer
                (String.format("Обновлена сущность студента с идентификатором = %d", student.getStudentId()));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.PUT);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudent(int id) {
        removeStudentFromAllTeachers(id);
        grpcClientService.sendMessageToServer(String.format("Удалена сущность студента с идентификатором = %d", id));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.DELETE);
        studentMapper.deleteStudent(id);
    }

    @Override
    public void removeStudentFromTeacher(int studentId, int teacherId) {
        grpcClientService.sendMessageToServer(String.format("Удалена связь студента с идентификатором = %d " +
                "и учителя с идентификатором = %d", studentId, teacherId));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.DELETE);
        studentMapper.removeStudentFromTeacher(studentId, teacherId);
    }

    @Override
    public void addStudentToTeacher(int studentId, int teacherId) {
        grpcClientService.sendMessageToServer(String.format("Добавлена связь студента с идентификатором = %d " +
                "и учитея с идентификатором = %d", studentId, teacherId));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.POST);
        studentMapper.addStudentToTeacher(studentId, teacherId);
    }

    @Override
    public List<TeacherDto> getTeachersByStudentId(int id) {
        List<Teacher> entities = studentMapper.getTeachersByStudentId(id);
        grpcClientService.sendMessageToServer(String.format("Получен список всех учителей студента с идентификатором = %d", id));
        jmsProducer.sendMessage(QUEUE_NAME, HttpMethod.GET);
        return modelMapper.map(entities, TypeToken.of(List.class).getType());
    }

    @Override
    public void removeStudentFromAllTeachers(int id) {
        grpcClientService.sendMessageToServer(String.format("Удаление сущности студента с идентификатором = %d", id));
        studentMapper.removeStudentFromAllTeachers(id);
    }


}
