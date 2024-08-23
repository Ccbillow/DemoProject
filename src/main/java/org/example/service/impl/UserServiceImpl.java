package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.example.common.BaseConstant;
import org.example.common.enums.ExceptionEnum;
import org.example.exception.BusinessException;
import org.example.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(UserRegisterRequest request) {
        try {
            User user = new User();
            //todo password encrypt
            BeanUtils.copyProperties(user, request);
            user.setDeleted(0);

            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("username", user.getUsername());
            List<User> existUserList = userMapper.selectList(queryWrapper);
            if (CollectionUtils.isNotEmpty(existUserList)) {
                throw new BusinessException(ExceptionEnum.USER_REGISTER_FAIL.getErrorCode(), "user already exist.");
            }

            userMapper.insert(user);
            // send email
            emailService.sendEmail(user.getEmail(), BaseConstant.EMAIL_SUBJECT, String.format(BaseConstant.EMAIL_CONTENT, user.getUsername()));
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
