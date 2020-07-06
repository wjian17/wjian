package com.wjian.study.oauth2server.advice;

import com.wjian.study.domain.basic.BasicResponse;
import com.wjian.study.domain.enums.StatusCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wangjian
 * @date 2020/6/22 0022 14:58
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public BasicResponse handleAccessDeniedException(AccessDeniedException e){
        return StatusCode.getBasicResponse(StatusCode.PERMISSION_NO_ACCESS);
    }
 
}