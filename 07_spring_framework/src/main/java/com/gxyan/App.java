package com.gxyan;

import com.gxyan.framework.SpringApplicationContext;
import com.gxyan.service.PrototypeService;
import com.gxyan.service.SingletonService;
import com.gxyan.service.UserService;

/**
 * @author gxyan
 * @date 2020/12/20
 */
public class App {
    public static void main(String[] args) {
        SpringApplicationContext context = new SpringApplicationContext(AppConfig.class);

        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService);

        SingletonService singletonService1 = (SingletonService) context.getBean("singletonService");
        SingletonService singletonService2 = (SingletonService) context.getBean("singletonService");
        System.out.println(singletonService1 == singletonService2);

        PrototypeService prototypeService1 = (PrototypeService) context.getBean("prototypeService");
        PrototypeService prototypeService2 = (PrototypeService) context.getBean("prototypeService");
        System.out.println(prototypeService1);
        System.out.println(prototypeService2);
        System.out.println(prototypeService1.getUserService());
        System.out.println(prototypeService2.getUserService());
    }
}
