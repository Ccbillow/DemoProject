package org.example.model.request;

/**
 * email send request
 */
public class EmailSendRequest {

    /**
     * email receiver
     */
    private String to;

    /**
     * email subject
     */
    private String subject;

    /**
     * email text
     */
    private String text;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
