package org.example.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel(value = "user register request", description = "user register request param")
public class UserRegisterRequest {

    /**
     * username
     */
    @ApiModelProperty(value = "username", required = true, example = "Mary")
    @NotBlank(message = "username can not be null")
    private String username;

    /**
     * password
     */
    @ApiModelProperty(value = "password", required = true, example = "Abc@123")
    @NotBlank(message = "password can not be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[`~!@#$%^&*()-=_+;':\",./<>?])(?=\\S+$).{6,}$", message = "The password must contain uppercase, lowercase, numbers and special characters and be at least 6 characters long")
    private String password;

    /**
     * email
     */
    @ApiModelProperty(value = "email", required = true, example = "mary@gmail.com")
    @NotBlank(message = "email can not be null")
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
