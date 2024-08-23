package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.model.domain.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    User selectByUserId(Long userId);

    /**
     * batch delete
     * logic
     * update deleted 0 -> -1
     * @param userIdList
     */
    void batchDelete(List<Long> userIdList);
}
