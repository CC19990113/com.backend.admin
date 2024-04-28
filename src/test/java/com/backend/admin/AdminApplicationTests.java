package com.backend.admin;

import com.backend.admin.entity.User;
import com.backend.admin.mapper.LoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AdminApplicationTests {
    @Autowired
    private LoginMapper loginMapper;
    @Test
    void contextLoads() {
        List<User> users = loginMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
