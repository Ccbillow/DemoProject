package org.example.exception;

import org.apache.tomcat.util.buf.StringUtils;
import org.example.common.enums.ExceptionEnum;
import org.example.model.response.CommonResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * global exception handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * handle param valid exception
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResponse bindExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(item -> {
            errors.add(item.getDefaultMessage());
        });
        String errorMessage = StringUtils.join(errors, '|');

        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setErrorCode(ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
        response.setErrorMsg(errorMessage);
        return response;
    }

    /**
     * handle pathvariable param exception
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public CommonResponse bindExceptionHandler(MethodArgumentTypeMismatchException e) {
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setErrorCode(ExceptionEnum.PARAM_ILLEGAL.getErrorCode());
        response.setErrorMsg(String.format("NumberFormatException: param:%s, value:%s, requiredType:%s", e.getName(), e.getValue(), e.getRequiredType()));
        return response;
    }

    /**
     * handle business exception
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResponse bizExceptionHandler(HttpServletRequest req, BusinessException e){
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setErrorCode(e.getErrorCode());
        response.setErrorMsg(e.getErrorMsg());
        return response;
    }

    /**
     * handle other exception
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse exceptionHandler(HttpServletRequest req, Exception e){
        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setErrorCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getErrorCode());
        response.setErrorMsg(ExceptionEnum.INTERNAL_SERVER_ERROR.getErrorMsg());
        return response;
    }
}
