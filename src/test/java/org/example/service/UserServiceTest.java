package org.example.service;

import org.example.common.enums.ExceptionEnum;
import org.example.exception.BusinessException;
import org.example.model.User;
import org.example.model.request.UserQueryRequest;
import org.example.model.request.UserRegisterRequest;
import org.example.model.request.UserUpdateRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAdd_Fail_UserExist() {
        BusinessException be = null;

        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername("Mick");
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

    @Test
    public void testUpdate_Fail_UserNotExist() {
        BusinessException be = null;

        UserUpdateRequest request = new UserUpdateRequest();
        request.setUserId(0L);
        request.setUsername("Test" + System.currentTimeMillis());
        request.setEmail("test@gmail.com");
        request.setPassword("Abc@123");
        try {
            userService.update(request);
        } catch (Exception e) {
            be = (BusinessException) e;
        }

        Assert.assertEquals(be.getErrorCode(), ExceptionEnum.USER_NOT_EXIST.getErrorCode());
        Assert.assertEquals(be.getErrorMsg(), ExceptionEnum.USER_NOT_EXIST.getErrorMsg());
    }

    @Test
    public void testUpdate_Success() {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setUserId(6L);
        request.setUsername("Mick");
        request.setEmail("mick@gmail.com");
        request.setPassword("Mick@123456");

        userService.update(request);
    }

    @Test
    public void testQueryByUserId_Success_Illegal_UserId() {
        Long userId = -1L;
        User user = userService.queryByUserId(userId);
        Assert.assertNull(user);
    }

    @Test
    public void testQueryByUserId_Success() {
        Long userId = 6L;
        User user = userService.queryByUserId(userId);
        Assert.assertNotNull(user);
        Assert.assertEquals(userId, user.getUserId());
    }

    @Test
    public void testDelete_Success_Null_UserId() {
        userService.delete(null);
    }

    @Test
    public void testDelete_Success() {
        List<Long> userIdList = new ArrayList<>();
        userIdList.add(7L);
        userService.delete(userIdList);
    }

    @Test
    public void testUserList_Success() {
        UserQueryRequest request = new UserQueryRequest();
        List<User> userList = userService.userList(request);
        Assert.assertNotNull(userList);
    }

    @Test
    public void testUserList_Success_Username() {
        UserQueryRequest request = new UserQueryRequest();
        request.setUsername("Mi");
        List<User> userList = userService.userList(request);
        Assert.assertNotNull(userList);
        Assert.assertEquals("Mick", userList.get(0).getUsername());
    }

    @Test
    public void testUserList_Success_IdList() {
        UserQueryRequest request = new UserQueryRequest();
        request.setUserIdList(Arrays.asList(6L, 8L));
        List<User> userList = userService.userList(request);
        Assert.assertNotNull(userList);
        Assert.assertEquals(2, userList.size());
    }

    @Test
    public void testUserList_Success_NotExist() {
        UserQueryRequest request = new UserQueryRequest();
        request.setUserIdList(Arrays.asList(-1L));
        List<User> userList = userService.userList(request);
        Assert.assertEquals(0, userList.size());
    }
}
