package org.example.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@ApiModel(value = "user update request", description = "user update request param")
public class UserUpdateRequest {

    /**
     * username
     */
    @ApiModelProperty(value = "username", example = "Mary")
    private String username;

    /**
     * password
     */
    @ApiModelProperty(value = "password", example = "Abc@123")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[`~!@#$%^&*()-=_+;':\",./<>?])(?=\\S+$).{6,}$", message = "The password must contain uppercase, lowercase, numbers and special characters and be at least 6 characters long")
    private String password;

    /**
     * email
     */
    @ApiModelProperty(value = "email", example = "mary@gmail.com")
    @Email(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Email format error")
    private String email;

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
