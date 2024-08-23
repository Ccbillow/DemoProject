package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.common.BaseConstant;
import org.example.common.enums.ExceptionEnum;
import org.example.exception.BusinessException;
import org.example.mapper.UserMapper;
import org.example.model.domain.User;
import org.example.model.request.UserQueryRequest;
import org.example.model.request.UserRegisterRequest;
import org.example.model.request.UserUpdateRequest;
import org.example.model.view.UserView;
import org.example.service.EmailService;
import org.example.service.UserService;
import org.example.util.convert.UserConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    Logger log = LogManager.getLogger(UserServiceImpl.class);

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
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());

            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("username", user.getUsername());
            queryWrapper.eq("deleted", 0);
            List<User> existUserList = userMapper.selectList(queryWrapper);
            if (CollectionUtils.isNotEmpty(existUserList)) {
                log.error(String.format("user add business exception, user already exist, username:%s", user.getUsername()));
                throw new BusinessException(ExceptionEnum.USER_REGISTER_FAIL.getErrorCode(), "user already exist.");
            }

            userMapper.insert(user);
            // send email
            emailService.sendEmail(user.getEmail(), BaseConstant.EMAIL_SUBJECT, String.format(BaseConstant.EMAIL_CONTENT, user.getUsername()));
        } catch (BusinessException be) {
            log.error(String.format("user add business exception, code:%s, msg:%s", be.getErrorCode(), be.getErrorMsg()));
            throw be;
        } catch (Exception e) {
            log.error(String.format("user add exception, error msg:%s", e.getMessage()));
            throw new BusinessException(ExceptionEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void update(Long userId, UserUpdateRequest request) {
        try {
            User user = userMapper.selectByUserId(userId);
            if (user == null) {
                log.error(String.format("user update business exception, user not exist, userId:%s", userId));
                throw new BusinessException(ExceptionEnum.USER_NOT_EXIST);
            }

            //todo password encrypt
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());
            user.setUsername(request.getUsername());
            user.setUpdateTime(new Date());
            userMapper.updateById(user);
        } catch (BusinessException be) {
            log.error(String.format("user update business exception, code:%s, msg:%s", be.getErrorCode(), be.getErrorMsg()));
            throw be;
        } catch (Exception e) {
            log.error(String.format("user update exception, error msg:%s", e.getMessage()));
            throw new BusinessException(ExceptionEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserView queryByUserId(Long userId) {
        if (userId == null || userId <= 0L) {
            log.warn("user queryByUserId, param is null or <= 0L");
            return null;
        }

        return UserConvertUtil.userToUserView(userMapper.selectByUserId(userId));
    }

    @Override
    public void delete(List<Long> userIdLIst) {
        if (CollectionUtils.isEmpty(userIdLIst)) {
            log.warn("user delete, param is empty");
            return;
        }

        userMapper.batchDelete(userIdLIst);
    }

    @Override
    public List<UserView> userList(UserQueryRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        if (StringUtils.isNotBlank(request.getUsername())) {
            queryWrapper.like("username", request.getUsername());
        }
        if (CollectionUtils.isNotEmpty(request.getUserIdList())) {
            queryWrapper.in("user_id", request.getUserIdList());
            queryWrapper.in("user_id", request.getUserIdList());
        }
        queryWrapper.eq("deleted", 0);
        return UserConvertUtil.userListToUserViewList(userMapper.selectList(queryWrapper));
    }
}
