package com.gxyan.framework;

import com.gxyan.framework.constant.ScopeEnum;

/**
 * Bean的定义（如Scope）
 * @author gxyan
 * @date 2020/12/20
 */
public class BeanDefinition {
    private Class beanClass;
    private ScopeEnum scope;

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public ScopeEnum getScope() {
        return scope;
    }

    public void setScope(ScopeEnum scope) {
        this.scope = scope;
    }
}
