package com.tablet.util;

import com.tablet.authorization.UserLoginHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.tablet.repository.domain.IAuditRepository;

import java.lang.annotation.Annotation;
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
            return Proxy.newProxyInstance(
                original.getClassLoader(),
                original.getInterfaces(), (proxy, method, args) -> {
                    Annotation annotation = original.getMethod(
                        method.getName(),
                        method.getParameterTypes()).getAnnotation(Audit.class);
                    boolean status = true;
                    Object result;
                    try {
                        result = method.invoke(bean, args);
                        System.out.println("TRUE");
                    } catch (Exception e) {
                        status = false;
                        System.out.println("FALSE");
                        throw e.getCause();
                    } finally {
                        if (annotation != null) {
                            String action = ((Audit) annotation).action();
                            Object[] newArgs = new Object[args.length + 1];
                            newArgs[0] = action;
                            System.arraycopy(args, 0, newArgs, 1, args.length);
                            iauditRepository.create(status, userLoginHolder.getCurrentUser(), newArgs);
                        }
                    }
                    return result;
                }
            );
        }
        return bean;
    }
}