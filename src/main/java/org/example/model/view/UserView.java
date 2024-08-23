package org.example.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * user view
 * obj to front
 */
@ApiModel(value = "user view", description = "user response")
public class UserView {

    @ApiModelProperty(value = "userId", example = "1")
    private Long userId;

    /**
     * username
     */
    @ApiModelProperty(value = "username", example = "Mary")
    private String username;

    /**
     * password
     */
    @ApiModelProperty(value = "password", example = "Abc@123")
    private String password;

    /**
     * email
     */
    @ApiModelProperty(value = "email", example = "Mary@gmail.com")
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
