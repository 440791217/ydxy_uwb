package org.xiaomi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xiaomi.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> findAll();
    /**
     * 插入用户数据
     * @param user 用户实体
     * @return 插入成功的记录数
     */
    @Insert("INSERT INTO user (name, passwd) VALUES (#{name}, #{passwd})")
    int insertUser(User user);

    /**
     * 根据用户名查询用户信息
     * @param name 用户名
     * @return 用户实体
     */
    @Select("SELECT * FROM user WHERE name = #{name}")
    User findUserByName(String name);
}