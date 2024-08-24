package org.example.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.common.enums.NoticeTypeEnum;
import org.example.model.request.NoticeRequest;
import org.springframework.stereotype.Component;

/**
 * handle notice using email
 *
 * not supporting now
 */
@Component
public class MessageNoticeHandler implements NoticeHandler {
    Logger log = LogManager.getLogger(MessageNoticeHandler.class);

    @Override
    public NoticeTypeEnum getNoticeType() {
        return NoticeTypeEnum.MESSAGE;
    }

    //todo send message by phone
    @Override
    public void handleNotice(NoticeRequest request) {
        log.warn("currently, system does not support notifying user by message.");
    }
}
