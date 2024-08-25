package org.example.controller;


import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.request.EmailSendRequest;
import org.example.model.response.CommonResponse;
import org.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * email controller
 */
@Api(tags = "email controller")
@RestController
public class EmailController {

    Logger log = LogManager.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "email send")
    @RequestMapping(value = "/email/send", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<Void> query(@RequestBody EmailSendRequest request) {
        CommonResponse<Void> result = new CommonResponse<>();

        log.info("email send controller, param:{}", JSON.toJSONString(request));
        emailService.sendEmail(request.getTo(), request.getSubject(), request.getText());
        result.setSuccess(true);
        log.info("email send controller, result:{}", JSON.toJSONString(result));
        return result;
    }

}
