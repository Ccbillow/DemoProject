package org.example.util.convert;

import org.example.model.domain.User;
import org.example.model.view.UserView;

import java.util.ArrayList;
import java.util.List;

public class UserConvertUtil {

    public static UserView userToUserView(User user) {
        if (user == null) {
            return null;
        }

        UserView result = new UserView();
        result.setUserId(user.getUserId());
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setEmail(user.getEmail());
        result.setDeleted(user.getDeleted());
        result.setCreateTime(user.getCreateTime());
        result.setUpdateTime(user.getUpdateTime());
        return result;
    }

    public static List<UserView> userListToUserViewList(List<User> userList) {
        if (userList == null || userList.isEmpty()) {
            return new ArrayList<>();
        }

        List<UserView> result = new ArrayList<>();
        userList.forEach(user -> result.add(userToUserView(user)));
        return result;
    }
}
