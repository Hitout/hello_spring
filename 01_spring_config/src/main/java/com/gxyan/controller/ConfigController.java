package com.gxyan.controller;

import com.gxyan.config.BeanProperties;
import com.gxyan.config.ConfigBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gxyan
 * @date 2020/5/6 17:11
 */
@RestController
public class ConfigController {

    @Resource
    private BeanProperties beanProperties;

    @Resource
    private ConfigBean configBean;

    @RequestMapping("/")
    public String index() {
        return configBean.getWholeTitle();
    }

    @RequestMapping("/bean")
    public String bean() {
        return beanProperties.getName() + "--" + beanProperties.getTitle();
    }
}
