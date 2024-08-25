package org.example.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * email send request
 */
@ApiModel(value = "email send request", description = "email send request param")
public class EmailSendRequest {

    /**
     * email receiver
     */
    @ApiModelProperty(value = "subject", example = "test@gmail.com")
    private String to;

    /**
     * email subject
     */
    @ApiModelProperty(value = "subject", example = "Test Subject")
    private String subject;

    /**
     * email text
     */
    @ApiModelProperty(value = "subject", example = "Test Text")
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
