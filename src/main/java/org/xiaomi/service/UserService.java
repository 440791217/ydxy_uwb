package org.xiaomi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaomi.entity.User;
import org.xiaomi.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }
    /**
     * 插入用户数据
     * @param user 用户实体
     * @return 插入成功的记录数
     */
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    /**
     * 验证用户名和密码
     * @param name 用户名
     * @param passwd 密码
     * @return 验证结果，true 表示验证通过，false 表示验证失败
     */
    public boolean validateUser(String name, String passwd) {
        User user = userMapper.findUserByName(name);
        return user != null && user.getPasswd().equals(passwd);
    }

    public boolean isNameDuplicate(String name) {
        User user = userMapper.findUserByName(name);
        return user != null;
    }
}