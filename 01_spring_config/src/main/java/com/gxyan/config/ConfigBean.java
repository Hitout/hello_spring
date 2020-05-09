package com.gxyan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 通过注解@ConfigurationProperties(prefix="")指明了属性的通用前缀(通用前缀加属性名和配置文件的属性名一一对应)
 * 并在Spring Boot入口类加上注解@EnableConfigurationProperties({XXX.class})来启用该配置
 *
 * @author gxyan
 * @date 2020/5/6 17:51
 */
@Data
@ConfigurationProperties(prefix = "hello.config")
public class ConfigBean {
    private String name;
    private String title;
    private String wholeTitle;
}
