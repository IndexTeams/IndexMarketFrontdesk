package cn.kiroe.index.market.frontdesk.controller;

import cn.kiroe.index.market.frontdesk.dao.vo.common.BaseRespVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author Kiro
 * @Date 2024/01/03 17:51
 **/
@RestControllerAdvice(basePackages = "cn.kiroe.index.market.frontdesk.controller")
@Slf4j
public class GlobalExceptionControllerAdvice {
    @ExceptionHandler(UnauthenticatedException.class)
    public BaseRespVo unauthenticatedException(UnauthenticatedException exception){
        log.warn("用户未登入: {}",exception.getMessage());
        return BaseRespVo.fail(exception.getMessage());
    }

    private static final String BAD_REQUEST_MSG = "客户端请求参数错误";
    // <1> 处理 form data方式调用接口校验失败抛出的异常
    @ExceptionHandler(BindException.class)
    public BaseRespVo bindExceptionHandler(BindException e) {
        log.warn("错误信息",e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                                          .map(o -> o.getDefaultMessage())
                                          .collect(Collectors.toList());
        return BaseRespVo.resp(HttpStatus.BAD_REQUEST.value(),collect.toString());
    }
    // <2> 处理 json 请求体调用接口校验失败抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseRespVo methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.warn("错误信息",e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                                          .map(o -> o.getDefaultMessage())
                                          .collect(Collectors.toList());
        return BaseRespVo.resp(HttpStatus.BAD_REQUEST.value(),collect.toString());
    }
    // <3> 处理单个参数校验失败抛出的异常
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseRespVo constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.warn("错误信息",e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                                                   .map(o -> o.getMessage())
                                                   .collect(Collectors.toList());
        return BaseRespVo.resp(HttpStatus.BAD_REQUEST.value(),collect.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseRespVo runtimeException(RuntimeException exception){
        log.warn("错误信息",exception);
        return BaseRespVo.fail(exception.getMessage());
    }


}
