package org.example.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.model.request.UserQueryRequest;
import org.example.model.request.UserRegisterRequest;
import org.example.model.request.UserUpdateRequest;
import org.example.model.response.CommonResponse;
import org.example.model.view.UserView;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * user controller
 */
@Api(tags = "user controller")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "user register")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<Void> register(@RequestBody @Valid UserRegisterRequest request) {
        CommonResponse<Void> result = new CommonResponse<>();
        userService.add(request);
        result.setSuccess(true);
        return result;
    }

    @ApiOperation(value = "user update")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResponse<Void> update(@PathVariable("id") Long userId, @RequestBody @Valid UserUpdateRequest request) {
        CommonResponse<Void> result = new CommonResponse<>();
        userService.update(userId, request);
        result.setSuccess(true);
        return result;
    }

    @ApiOperation(value = "user delete", notes = "logic delete")
    @RequestMapping(value = "/users/{idList}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResponse<Void> delete(@PathVariable("idList") List<Long> userIdList) {
        CommonResponse<Void> result = new CommonResponse<>();
        userService.delete(userIdList);
        result.setSuccess(true);
        return result;
    }

    @ApiOperation(value = "query one user", notes = "using userId to query one user")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<UserView> queryById(@PathVariable("id") Long userId) {
        CommonResponse<UserView> result = new CommonResponse<>();
        UserView user = userService.queryByUserId(userId);
        result.setSuccess(true);
        result.setData(user);
        return result;
    }

    @ApiOperation(value = "query user list")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<List<UserView>> query(@RequestBody UserQueryRequest request) {
        CommonResponse<List<UserView>> result = new CommonResponse<>();
        List<UserView> userList = userService.userList(request);
        result.setSuccess(true);
        result.setData(userList);
        return result;
    }

}
