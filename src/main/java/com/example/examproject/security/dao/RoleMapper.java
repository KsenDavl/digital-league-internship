package com.example.examproject.security.dao;


import com.example.examproject.security.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper {

    @Select("select * from roles where role_id = #{id}")
    Role getRoleById(int id);

}
