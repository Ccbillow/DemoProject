package org.example.service;

import org.example.model.User;
import org.example.model.request.UserRegisterRequest;
import org.example.model.request.UserUpdateRequest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserService {

    void add(UserRegisterRequest request);

    void update(UserUpdateRequest request);

    User queryByUserId(Long userId);

    void delete(List<Long> userIdLIst);

    List<User> userList();
}
