package org.example.handler;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.common.BaseConstant;
import org.example.common.enums.NoticeTypeEnum;
import org.example.model.request.NoticeRequest;
import org.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * handle notice using email
 */
@Component
public class EmailNoticeHandler implements NoticeHandler{
    Logger log = LogManager.getLogger(MessageNoticeHandler.class);

    @Autowired
    private EmailService emailService;

    @Override
    public NoticeTypeEnum getNoticeType() {
        return NoticeTypeEnum.EMAIL;
    }

    @Override
    public void handleNotice(NoticeRequest request) {
        log.info("notice user by email, param:{}", JSON.toJSONString(request));
        emailService.sendEmail(request.getEmail(), BaseConstant.EMAIL_SUBJECT,
                String.format(BaseConstant.EMAIL_CONTENT, request.getUsername()));
    }
}
