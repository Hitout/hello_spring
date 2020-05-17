package com.gxyan.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过 @ControllerAdvice/@RestControllerAdvice 实现全局异常处理
 * @author gxyan
 * @date 2020/5/17 16:18
 */
@Slf4j
@RestControllerAdvice
public class MyRestExceptionController {

    /**
     * 处理所有的Controller层面的异常
     */
    @ExceptionHandler(Exception.class)
    public final ModelAndView handleAllException(Exception e, HttpServletRequest request) {
        log.error(e.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", e.getMessage());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
