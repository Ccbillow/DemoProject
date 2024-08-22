package org.example.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.example.common.enums.ExceptionEnum;
import org.example.exception.BusinessException;
import org.example.model.User;
import org.example.model.request.UserQueryRequest;
import org.example.model.request.UserRegisterRequest;
import org.example.model.request.UserUpdateRequest;
import org.example.service.EmailService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmailService emailService;

    @Override
    public void add(UserRegisterRequest request) {
        System.out.println("add");

        try {
            User user = new User();
            //todo password encrypt
            BeanUtils.copyProperties(user, request);
            user.setDeleted(0);

            //todo send email
            emailService.sendEmail(user.getEmail(), "DemoProject-Test-Email", String.format("hey %s, congratulation! you register success!", user.getUsername()));
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void update(UserUpdateRequest request) {
        System.out.println("update");

        try {
            User user = new User();
            //todo password encrypt
            BeanUtils.copyProperties(user, request);
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public User queryByUserId(Long userId) {
        System.out.println("queryByUserId");
        return null;
    }

    @Override
    public void delete(List<Long> userIdLIst) {
        System.out.println("delete");
    }

    @Override
    public List<User> userList(UserQueryRequest request) {
        System.out.println("userList");
        return null;
    }
}
