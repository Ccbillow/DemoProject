package org.example.handler;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.common.BaseConstant;
import org.example.common.enums.NoticeTypeEnum;
import org.example.model.request.EmailSendRequest;
import org.example.model.request.NoticeRequest;
import org.example.model.response.CommonResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * handle notice using email
 */
@Component
public class EmailNoticeHandler implements NoticeHandler{
    Logger log = LogManager.getLogger(MessageNoticeHandler.class);

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Override
    public NoticeTypeEnum getNoticeType() {
        return NoticeTypeEnum.EMAIL;
    }

    @Override
    public void handleNotice(NoticeRequest request) {
        log.info("notice user by email, param:{}", JSON.toJSONString(request));
        EmailSendRequest sendRequest = new EmailSendRequest();
        sendRequest.setTo(request.getEmail());
        sendRequest.setSubject(BaseConstant.EMAIL_SUBJECT);
        sendRequest.setText(String.format(BaseConstant.EMAIL_CONTENT, request.getUsername()));
        CommonResponse result = getRestTemplate().postForObject(
                "http://localhost:8082/email/send", sendRequest, CommonResponse.class);
        log.info("notice user by email, response:{}", JSON.toJSONString(result));
    }
}
