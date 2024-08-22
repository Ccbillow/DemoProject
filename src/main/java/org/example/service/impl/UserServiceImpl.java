package org.example.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.example.exception.DemoBusinessException;
import org.example.model.User;
import org.example.model.request.UserRegisterRequest;
import org.example.model.request.UserUpdateRequest;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void add(UserRegisterRequest request) {
        System.out.println("add");

        try {
            User user = new User();
            //todo password encrypt
            BeanUtils.copyProperties(user, request);

            //todo send email
        } catch (Exception e) {
            throw new DemoBusinessException(e);
        }
    }

    @Override
    public void update(UserUpdateRequest request) {
        System.out.println("update");

        try {
            User user = new User();
            //todo password encrypt
            BeanUtils.copyProperties(user, request);

            //todo send email
        } catch (Exception e) {
            throw new DemoBusinessException(e);
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
    public List<User> userList() {
        System.out.println("userList");
        return null;
    }
}
