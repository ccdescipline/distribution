package com.distribution.web.Config;

import com.distribution.common.responsResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class MyExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public responsResult processExcption(Exception e) {
        //System.out.println(e.getMessage());
        e.printStackTrace();

        return responsResult.error(e.getMessage());
    }

    /**
     * 处理RequestBody validate的校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public responsResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder str = new StringBuilder();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        String collect = allErrors.stream().map((x) -> x.getDefaultMessage()).collect(Collectors.joining(","));

        return responsResult.error(collect);
    }

}