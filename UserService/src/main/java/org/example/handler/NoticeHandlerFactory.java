package org.example.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.common.enums.NoticeTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * notice handler factory
 * using NoticeType get NoticeHandler
 */
@Component
public class NoticeHandlerFactory implements InitializingBean, ApplicationContextAware {
    Logger log = LogManager.getLogger(NoticeHandlerFactory.class);

    /**
     * notice handler map
     * key - notice type
     * value - notice handler
     */
    private static final Map<NoticeTypeEnum, NoticeHandler> NOTICE_HANDLER_MAP = new EnumMap<>(NoticeTypeEnum.class);
    private ApplicationContext appContext;

    public NoticeHandler getHandler(NoticeTypeEnum noticeType) {
        if (!NOTICE_HANDLER_MAP.containsKey(noticeType)) {
            log.error("NoticeType:{} is not supported by the system, please contact developer.", noticeType);
        }

        return NOTICE_HANDLER_MAP.get(noticeType);
    }

    /**
     * after application context start, put beans which implement NoticeHandler into NOTICE_HANDLER_MAP
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        appContext.getBeansOfType(NoticeHandler.class)
                .values()
                .forEach(handler -> NOTICE_HANDLER_MAP.put(handler.getNoticeType(), handler));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        appContext = applicationContext;
    }
}
