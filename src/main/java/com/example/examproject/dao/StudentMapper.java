package com.example.examproject.dao;

import com.example.examproject.entity.Student;
import com.example.examproject.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface StudentMapper {

    @Select("select * from students")
    List<Student> getAllStudents();

    @Select("select * from students where student_id = #{id}")
    Student getStudentById(int id);

    @Insert("insert into students values (#{studentId}, #{name}, #{specialty}, #{year})")
    @SelectKey(keyProperty = "studentId", before = true, resultType = Integer.class,
            statement = "select nextval('students_id_seq')")
    void addStudent(Student student);

    @Update("update students set name = #{name}, specialty = #{specialty}," +
                                            " year = #{year} where student_id = #{studentId}" )
    void updateStudent(Student student);

    @Delete("delete from students where student_id = #{id} ")
    void deleteStudent(int id);

    @Select("select t.teacher_id, t.name, department from teachers t" +
            " join students_teachers st on t.teacher_id = st.teacher_id where student_id = #{id};")
    List<Teacher> getTeachersByStudentId(int id);

    @Insert("insert into students_teachers values (#{studentId}, #{teacherId})")
    void addStudentToTeacher(int studentId, int teacherId);

    @Delete("delete from students_teachers where student_id = #{studentId} AND " +
            "teacher_id = #{teacherId}")
    void removeStudentFromTeacher(int studentId, int teacherId);

    @Delete("delete from students_teachers where student_id = #{id}")
    void removeStudentFromAllTeachers(int id);

}
