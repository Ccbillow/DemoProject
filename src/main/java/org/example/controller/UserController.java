package org.example.controller;


import org.example.model.User;
import org.example.model.request.UserRegisterRequest;
import org.example.model.request.UserUpdateRequest;
import org.example.model.response.CommonRes;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonRes<Void> register(@RequestBody @Valid UserRegisterRequest request) {
        CommonRes<Void> result = new CommonRes<>();
        userService.add(request);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonRes<Void> update(@RequestBody UserUpdateRequest request) {
        CommonRes<Void> result = new CommonRes<>();
        userService.update(request);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonRes<Void> delete(@RequestParam List<Long> userIdList) {
        CommonRes<Void> result = new CommonRes<>();
        userService.delete(userIdList);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    @ResponseBody
    public CommonRes<User> queryById(@RequestParam Long userId) {
        CommonRes<User> result = new CommonRes<>();
        User user = userService.queryByUserId(userId);
        result.setSuccess(true);
        result.setData(user);
        return result;
    }



    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public CommonRes<List<User>> query() {
        CommonRes<List<User>> result = new CommonRes<>();
        List<User> userList = userService.userList();
        result.setSuccess(true);
        result.setData(userList);
        return result;
    }

}
