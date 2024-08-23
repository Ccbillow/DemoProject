package org.example.service;

import org.example.model.request.UserQueryRequest;
import org.example.model.request.UserRegisterRequest;
import org.example.model.request.UserUpdateRequest;
import org.example.model.view.UserView;

import java.util.List;

public interface UserService {

    void add(UserRegisterRequest request);

    void update(Long userId, UserUpdateRequest request);

    UserView queryByUserId(Long userId);

    void delete(List<Long> userIdLIst);

    List<UserView> userList(UserQueryRequest request);
}
