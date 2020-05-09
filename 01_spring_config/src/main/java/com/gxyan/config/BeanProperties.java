package com.gxyan.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 通过@Value("${属性名}")来加载配置文件中的属性值
 *
 * @author gxyan
 * @date 2020/5/6 16:14
 */
@Data
@Component
public class BeanProperties {

    @Value("${hello.config.name}")
    private String name;

    @Value("${hello.config.title}")
    private String title;
}
