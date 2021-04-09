package com.example.examproject.dao;

import com.example.examproject.entity.Student;
import com.example.examproject.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TeacherMapper {

    @Select("select * from teachers")
    List<Teacher> getAllTeachers();

    @Select("select * from teachers where teacher_id = #{id}")
    Teacher getTeacherById(int id);

    @Insert("insert into teachers values (#{teacherId}, #{name}, #{department})")
    @SelectKey(keyProperty = "teacherId", before = true, resultType = Integer.class,
            statement = "select nextval('teachers_id_seq')")
    void addTeacher(Teacher teacher);

    @Update("update teachers set name = #{name}, department = #{department} where teacher_id = #{teacherId}")
    void updateTeacher(Teacher teacher);

    @Delete("delete from teachers where  teacher_id = #{id}")
    void deleteTeacherById(int id);

    @Select("select s.student_id, s.name, specialty, year from students s " +
            "join students_teachers st on s.student_id = st.student_id " +
            "where teacher_id = #{id}")
    List<Student> getStudentsByTeacherId(int id);

    @Delete("delete from students_teachers where teacher_id = #{id}")
    void deleteTeacherFromAllStudents(int id);
}
