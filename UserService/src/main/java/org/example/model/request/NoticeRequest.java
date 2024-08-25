package org.example.model.request;

/**
 * notice request param
 */
public class NoticeRequest {

    /**
     * email
     */
    private String email;

    /**
     * phone
     */
    private String phone;

    /**
     * username
     */
    private String username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
