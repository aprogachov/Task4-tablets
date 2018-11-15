package com.tablet.util;

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
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuditAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    @Lazy
    private IAuditRepository iauditRepository;

    private Map<String, Object> beans = new HashMap<>();

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
                    AuditOperation auditOperation = new AuditOperation();
                    auditOperation.setAction(((Audit) annotation).action());
//                    auditOperation.setDateAuditOperation(new Date(System.currentTimeMillis()));
                    auditOperation.setDateAuditOperation(Calendar.getInstance().getTime());
                    try {
                        Object result = method.invoke(bean, args);
                        System.out.println("TRUE");
                        auditOperation.setStatus(true);
                        iauditRepository.create(auditOperation);
                        System.out.println(auditOperation);
                        return result;
                    } catch (Exception e) {
                        System.out.println("FALSE");
                        auditOperation.setStatus(false);
                        iauditRepository.create(auditOperation);
                        System.out.println(auditOperation);
                        throw e;
                    }

//                    } catch (InvocationTargetException ite) {
//                        if (ite.getCause() instanceof StateException) {
//                            StateException cause = (StateException) ite.getCause();
//                            System.out.println(cause);
////                            cause.printStackTrace();
//                        }
//                        System.out.println("FALSE");
//                        auditOperation.setStatus("FALSE");
//                        iauditOperationService.addAuditOperation(auditOperation);
//                        System.out.println(auditOperation);
//                        throw ite;
//                    }
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