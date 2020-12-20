package com.gxyan.framework;

import com.gxyan.framework.annotation.Autowired;
import com.gxyan.framework.annotation.Component;
import com.gxyan.framework.annotation.ComponentScan;
import com.gxyan.framework.annotation.Scope;
import com.gxyan.framework.constant.ScopeEnum;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gxyan
 * @date 2020/12/20
 */
public class SpringApplicationContext {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private Map<String, Object> singletonBeanMap = new ConcurrentHashMap<>();

    public SpringApplicationContext(Class configClass) {
        // 扫描类得到 BeanDefinition
        scan(configClass);

        // 实例化非懒加载单例bean
        instanceSingletonBean();
    }

    private void instanceSingletonBean() {
        beanDefinitionMap.forEach((beanName, definition) -> {
            if (definition.getScope().equals(ScopeEnum.singleton)) {
                Object bean = doCreateBean(beanName, definition);
                singletonBeanMap.put(beanName, bean);
            }
        });
    }

    private Object doCreateBean(String beanName, BeanDefinition definition) {
        Class beanClass = definition.getBeanClass();
        try {
            // 实例化
            Object instance = beanClass.getDeclaredConstructor().newInstance();

            // 填充属性（依赖注入）
            Field[] fields = beanClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(field.getName());
                    field.setAccessible(true);
                    field.set(instance, bean);
                }
            }

            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void scan(Class configClass) {
        // 1、通过ComponentScan得到包路径
        ComponentScan componentScan = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String packagePath = componentScan.packagePath();

        // 2、扫描包路径得到classList，遍历得到BeanDefinition
        List<Class> classList = genBeanClasses(packagePath);
        for (Class clazz : classList) {
            if (clazz.isAnnotationPresent(Component.class)) {
                BeanDefinition beanDefinition = new BeanDefinition();
                Component component = (Component) clazz.getAnnotation(Component.class);
                String beanName = component.value();
                if (clazz.isAnnotationPresent(Scope.class)) {
                    Scope scope = (Scope) clazz.getAnnotation(Scope.class);
                    String scopeValue = scope.value();
                    if (ScopeEnum.prototype.name().equals(scopeValue)) {
                        beanDefinition.setScope(ScopeEnum.prototype);
                    } else {
                        beanDefinition.setScope(ScopeEnum.singleton);
                    }
                } else {
                    beanDefinition.setScope(ScopeEnum.singleton);
                }
                beanDefinition.setBeanClass(clazz);
                beanDefinitionMap.put(beanName, beanDefinition);
            }
        }
    }

    private List<Class> genBeanClasses(String packagePath) {
        List<Class> classes = new ArrayList<Class>();

        ClassLoader classLoader = SpringApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(packagePath.replace(".", "/"));
        File[] files = new File(resource.getFile()).listFiles();
        for (File file : files) {
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.endsWith(".class")) {
                String className = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"))
                        .replace("\\", ".");
                try {
                    Class<?> clazz = classLoader.loadClass(className);
                    classes.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return classes;
    }

    public Object getBean(String beanName) {
        if (singletonBeanMap.containsKey(beanName)) {
            return singletonBeanMap.get(beanName);
        } else {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            return doCreateBean(beanName, beanDefinition);
        }
    }
}
