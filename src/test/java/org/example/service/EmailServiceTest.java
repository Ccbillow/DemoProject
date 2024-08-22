package org.example.service;

import org.example.common.enums.ExceptionEnum;
import org.example.exception.BusinessException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceTest {

    @Resource
    private EmailService emailService;

    @Test
    public void testSendEmail_Fake_SMTP() {
        emailService.sendEmail("ctaoaoo@icloud.com", "Test", "test context.");
    }

    @Test
    public void testSendEmail_Param_Illegal() {
        BusinessException be = null;
        try {
            emailService.sendEmail("ctaoaoo@icloud.com", null, "test context.");
        } catch (Exception e) {
            be = (BusinessException) e;
        }

        Assert.assertEquals(be.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
    }

    @Test
    public void testSendEmail_Email_Illegal() {
        BusinessException be = null;
        try {
            emailService.sendEmail("aaabbbccc", "Test", "test context.");
        } catch (Exception e) {
            be = (BusinessException) e;
        }

        Assert.assertEquals(be.getErrorCode(), ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
        Assert.assertEquals(be.getErrorMsg(), "email is illegal");
    }
}
