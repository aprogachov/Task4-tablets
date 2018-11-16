package com.tablet.util;

import com.tablet.authorization.UserLoginHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.tablet.exception.StateException;
import com.modelsale.model.AuditOperation;
import com.tablet.repository.domain.IAuditRepository;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuditAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    @Lazy
    private IAuditRepository iauditRepository;

    private Map<String, Object> beans = new HashMap<>();

    @Autowired
    @Lazy
    private UserLoginHolder userLoginHolder;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        boolean hasAudMethod = Arrays.stream(bean.getClass().getMethods())
                .anyMatch(m -> m.isAnnotationPresent(Audit.class));
        if (hasAudMethod) {
            beans.put(beanName, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beans.containsKey(beanName)) {
            Class original = beans.get(beanName).getClass();
            Object beanProxy = Proxy.newProxyInstance(
                original.getClassLoader(),
                original.getInterfaces(), (proxy, method, args) -> {
                    Annotation annotation = original.getMethod(
                        method.getName(),
                        method.getParameterTypes()).getAnnotation(Audit.class);
                if (annotation != null) {
                    boolean success = true;
                    try {
                        Object result = method.invoke(bean, args);
                        System.out.println("TRUE");
                        iauditRepository.create(userLoginHolder.getCurrentUser(),success, args);
                        return result;
                    } catch (Exception e) {
                        System.out.println("FALSE");
                        success = false;
                        iauditRepository.create(userLoginHolder.getCurrentUser(),success, args);
                        throw e.getCause();
                    }
                }
                else {
                    return method.invoke(bean, args);
                }
                });
            return beanProxy;
        }
        return bean;
    }
}