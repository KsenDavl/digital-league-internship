package com.example.examproject.security.dao;

import com.example.examproject.security.model.Role;
import com.example.examproject.security.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Mapper
@Repository
public interface UserMapper {

    @Select("select user_id, username, password  from users")
    List<User> getAllUsers();

    @Select("select user_id, username, password  from users where user_id = #{userId}")
    User getUserById(int userId);

    @Insert("insert into users values (#{userId}, #{username}, #{password})")
    @SelectKey(keyProperty = "userId", before = true, resultType = Integer.class,
            statement = "select nextval('user_id_seq')")
    void addUser(User user);

    @Insert("insert into users_roles values (#{userId}, #{roleId})")
    void addRoleToUser(int userId, int roleId);

    @Update("update users set username = #{username}, password = #{password} where user_id = #{userId}")
    void updateUser(User user);

    @Delete("delete from users where user_id = #{userId}")
    void deleteUser(int userId);

    @Select("SELECT a.user_id, a.username, a.password, r.role_id, r.role_name " +
            "FROM users a join users_roles ur on a.user_id = ur.user_id " +
            "join roles r on ur.role_id = r.role_id WHERE username = #{username}")
    Optional<User> getUserByUsername(String username);

    @Select("select r.role_id, r.role_name from roles r join users_roles ur on r.role_id = ur.role_id where ur.user_id = #{userId}")
    Set<Role> getRolesByUserid(int userId);

    @Select("select user_id, username, password from users where username = #{login}")
    Optional<User> getByLogin(String login);
}
