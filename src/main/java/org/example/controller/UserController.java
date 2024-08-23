package org.example.controller;


import org.example.model.domain.User;
import org.example.model.request.UserQueryRequest;
import org.example.model.request.UserRegisterRequest;
import org.example.model.request.UserUpdateRequest;
import org.example.model.response.CommonResponse;
import org.example.model.view.UserView;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * user controller
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<Void> register(@RequestBody @Valid UserRegisterRequest request) {
        CommonResponse<Void> result = new CommonResponse<>();
        userService.add(request);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<Void> update(@RequestBody @Valid UserUpdateRequest request) {
        CommonResponse<Void> result = new CommonResponse<>();
        userService.update(request);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<Void> delete(@RequestParam @NotNull(message = "userIdList cannot be null") List<Long> userIdList) {
        CommonResponse<Void> result = new CommonResponse<>();
        userService.delete(userIdList);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<UserView> queryById(@RequestParam @NotNull(message = "userId cannot be null") Long userId) {
        CommonResponse<UserView> result = new CommonResponse<>();
        UserView user = userService.queryByUserId(userId);
        result.setSuccess(true);
        result.setData(user);
        return result;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<List<UserView>> query(UserQueryRequest request) {
        CommonResponse<List<UserView>> result = new CommonResponse<>();
        List<UserView> userList = userService.userList(request);
        result.setSuccess(true);
        result.setData(userList);
        return result;
    }

}
