package org.example.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel(value = "user query request", description = "user query request param")
public class UserQueryRequest {

    /**
     * user id list
     */
    @ApiModelProperty(value = "userIdList", example = "[1, 2, 3]")
    private List<Long> userIdList;

    /**
     * username
     * fuzzy match
     */
    @ApiModelProperty(value = "username", example = "Ma")
    private String username;

    public List<Long> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Long> userIdList) {
        this.userIdList = userIdList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
