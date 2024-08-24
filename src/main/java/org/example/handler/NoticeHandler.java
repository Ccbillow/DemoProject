package org.example.handler;

import org.example.common.enums.NoticeTypeEnum;
import org.example.model.request.NoticeRequest;

public interface NoticeHandler {

    /**
     * get notice type
     * @return
     */
    NoticeTypeEnum getNoticeType();

    /**
     * handle notice using different ways
     * @param request
     */
    void handleNotice(NoticeRequest request);
}
