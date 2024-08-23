package org.example.service;

import org.example.common.enums.ExceptionEnum;
import org.example.exception.BusinessException;
import org.example.model.request.UserRegisterRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAdd_Fail_UserExist() {
        BusinessException be = null;

        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername("Mary");
        request.setEmail("aaa@gmail.com");
        request.setPassword("Abc123");
        try {
            userService.add(request);
        } catch (Exception e) {
            be = (BusinessException) e;
        }

        Assert.assertEquals(be.getErrorCode(), ExceptionEnum.USER_REGISTER_FAIL.getErrorCode());
        Assert.assertEquals(be.getErrorMsg(), "user already exist.");
    }

    @Test
    public void testAdd_Success() {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername("Test" + System.currentTimeMillis());
        request.setEmail("test@gmail.com");
        request.setPassword("Abc@123");
        userService.add(request);
    }
}
