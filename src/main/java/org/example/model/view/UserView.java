package org.example.model.view;

/**
 * user view
 * obj to front
 */
public class UserView {

    private Long userId;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * email
     */
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
