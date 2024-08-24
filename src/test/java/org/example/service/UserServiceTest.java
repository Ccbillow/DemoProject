package org.example.service;

import org.example.common.enums.ExceptionEnum;
import org.example.exception.BusinessException;
import org.example.mapper.UserMapper;
import org.example.model.domain.User;
import org.example.model.request.UserQueryRequest;
import org.example.model.request.UserRegisterRequest;
import org.example.model.request.UserUpdateRequest;
import org.example.model.view.UserView;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

/**
 * User Service Test
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private EmailService emailService;

    @Test
    public void testAdd_Fail_UserExist() {
        BusinessException be = null;

        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername("Mick");
        request.setEmail("aaa@gmail.com");
        request.setPassword("Abc@123");
        try {
            List<User> userList = new ArrayList<>();
            User user = new User();
            user.setUserId(1L);
            user.setUsername("Mick");
            user.setPassword("Abc@123");
            user.setEmail("aaa@gmail.com");
            user.setDeleted(0);
            userList.add(user);
            Mockito.doReturn(userList).when(userMapper).selectList(any());
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

        Mockito.doReturn(new ArrayList<>()).when(userMapper).selectList(any());
        Mockito.doReturn(1).when(userMapper).insert(any());
        Mockito.doNothing().when(emailService).sendEmail(any(), any(), any());
        userService.add(request);
    }

    @Test
    public void testUpdate_Fail_UserNotExist() {
        BusinessException be = null;

        UserUpdateRequest request = new UserUpdateRequest();
        request.setUsername("Test" + System.currentTimeMillis());
        request.setEmail("test@gmail.com");
        request.setPassword("Abc@123");
        try {
            Mockito.doReturn(null).when(userMapper).selectByUserId(any());
            userService.update(0L, request);
        } catch (Exception e) {
            be = (BusinessException) e;
        }

        Assert.assertEquals(be.getErrorCode(), ExceptionEnum.USER_NOT_EXIST.getErrorCode());
        Assert.assertEquals(be.getErrorMsg(), ExceptionEnum.USER_NOT_EXIST.getErrorMsg());
    }

    @Test
    public void testUpdate_Success() {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setUsername("Mick");
        request.setEmail("mick@gmail.com");
        request.setPassword("Mick@123456");

        User user = new User();
        user.setUserId(1L);
        user.setUsername("Mick");
        user.setPassword("Abc@123111");
        user.setEmail("test111@gmail.com");
        user.setDeleted(0);
        Mockito.doReturn(user).when(userMapper).selectByUserId(any());
        Mockito.doReturn(1).when(userMapper).updateById(any());
        userService.update(1L, request);
    }

    @Test
    public void testQueryByUserId_Success_Illegal_UserId() {
        Long userId = -1L;
        Mockito.doReturn(null).when(userMapper).selectByUserId(userId);
        UserView user = userService.queryByUserId(userId);
        Assert.assertNull(user);
    }

    @Test
    public void testQueryByUserId_Success() {
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);
        user.setUsername("Mick");
        user.setPassword("Abc@123111");
        user.setEmail("test111@gmail.com");
        user.setDeleted(0);
        Mockito.doReturn(user).when(userMapper).selectByUserId(any());
        UserView result = userService.queryByUserId(userId);
        Assert.assertNotNull(result);
        Assert.assertEquals(userId, result.getUserId());
    }

    @Test
    public void testDelete_Success_Null_UserId() {
        userService.delete(null);
    }

    @Test
    public void testDelete_Success() {
        List<Long> userIdList = new ArrayList<>();
        userIdList.add(7L);
        Mockito.doNothing().when(userMapper).batchDelete(userIdList);
        userService.delete(userIdList);
    }

    @Test
    public void testUserList_Success() {
        UserQueryRequest request = new UserQueryRequest();
        Mockito.doReturn(getUserList()).when(userMapper).selectList(any());
        List<UserView> result = userService.userList(request);
        Assert.assertNotNull(result);
    }

    @Test
    public void testUserList_Success_Username() {
        UserQueryRequest request = new UserQueryRequest();
        request.setUsername("M");
        Mockito.doReturn(getUserList()).when(userMapper).selectList(any());
        List<UserView> userList = userService.userList(request);
        Assert.assertNotNull(userList);
        Assert.assertEquals("Mick", userList.get(0).getUsername());
    }

    @Test
    public void testUserList_Success_IdList() {
        UserQueryRequest request = new UserQueryRequest();
        request.setUserIdList(Arrays.asList(1L, 2L));
        Mockito.doReturn(getUserList()).when(userMapper).selectList(any());
        List<UserView> userList = userService.userList(request);
        Assert.assertNotNull(userList);
        Assert.assertEquals(2, userList.size());
    }

    @Test
    public void testUserList_Success_NotExist() {
        UserQueryRequest request = new UserQueryRequest();
        request.setUserIdList(Collections.singletonList(-1L));
        Mockito.doReturn(new ArrayList<>()).when(userMapper).selectList(any());
        List<UserView> userList = userService.userList(request);
        Assert.assertEquals(0, userList.size());
    }

    private static List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setUserId(1L);
        user.setUsername("Mick");
        user.setPassword("Abc@123");
        user.setEmail("Mick@gmail.com");
        user.setDeleted(0);
        userList.add(user);

        User user1 = new User();
        user1.setUserId(2L);
        user1.setUsername("Mary");
        user1.setPassword("Abc@123456");
        user1.setEmail("Mary@gmail.com");
        user1.setDeleted(0);
        userList.add(user1);
        return userList;
    }
}
