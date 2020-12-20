package com.gxyan.service;

import com.gxyan.framework.annotation.Autowired;
import com.gxyan.framework.annotation.Component;
import com.gxyan.framework.annotation.Scope;

/**
 * @author gxyan
 * @date 2020/12/20
 */
@Scope("prototype")
@Component("prototypeService")
public class PrototypeService {
    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }
}
