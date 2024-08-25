package org.example.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.common.enums.ExceptionEnum;
import org.example.exception.BusinessException;
import org.example.service.EmailService;
import org.example.util.EmailCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    Logger log = LogManager.getLogger(EmailServiceImpl.class);

    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {
        if (StringUtils.isBlank(to) || StringUtils.isBlank(subject) || StringUtils.isBlank(text)) {
            log.error("send email business exception, param should not be null, to:%s, subject:%s, text:%s", to, subject, text);
            throw new BusinessException(ExceptionEnum.PARAM_ILLEGAL);
        }

        if (!EmailCheckUtil.isValidEmail(to)) {
            log.error("send email business exception, email is invalid. to:%s", to);
            throw new BusinessException(ExceptionEnum.PARAM_ILLEGAL.getErrorCode(), "email is illegal");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            log.error("send email exception, error msg:%s", e.getMessage());
            throw new BusinessException(ExceptionEnum.EMAIL_SEND_FAIL);
        }
    }
}
