package com.zhaojm.study.config.handler;

import com.zhaojm.study.config.common.BusException;
import com.zhaojm.study.config.common.ErrorEnum;
import com.zhaojm.study.config.common.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * 统一处理Rest请求异常，封装异常内容返回
 *
 * @author zhaojm
 * @date 2020/7/5 19:26
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T>ResultDto<T> runtimeExceptionHandler(Exception e) {
        LOGGER.error("========> huge error! <========", e);
        return ResultDto.valueError(ErrorEnum.sys_default_error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private <T> ResultDto<T> illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error("========> invalid request! <========", e);
        //消息转换
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMsg.append(fieldError.getDefaultMessage());
            errorMsg.append("; ");
        }
        return ResultDto.valueError(ErrorEnum.bus_param_error, errorMsg.toString());
    }

    @ExceptionHandler(BusException.class)
    @ResponseBody
    private <T> ResultDto<T> BusinessExceptionHandler(BusException e) {
        LOGGER.error("========> business error! <========", e);
        return ResultDto.valueError(e.getErrorEnum(), e.getMessage());
    }
}
