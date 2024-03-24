//package com.amalitec.amalitececom.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.http.HttpServletRequest;
//
//@Component
//public class IpLoggingComponent {
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @PostConstruct
//    public void logAllBeans() {
//        String[] beanNames = applicationContext.getBeanNamesForAnnotation(RestController.class);
//        for (String beanName : beanNames) {
//            Object bean = applicationContext.getBean(beanName);
//            logBean(bean);
//        }
//    }
//
//    private void logBean(Object bean) {
//        // Check if the bean has a method annotated with @ModelAttribute
//        Class<?> clazz = bean.getClass();
//        for (var method : clazz.getDeclaredMethods()) {
//            if (method.isAnnotationPresent(ModelAttribute.class)) {
//                HttpServletRequest request = null; // You can get the request from your application context or inject it
//                String clientIp = request.getHeader("X-Forwarded-For");
//                if (clientIp == null || clientIp.isEmpty()) {
//                    clientIp = request.getRemoteAddr();
//                }
//                System.out.println("Client IP Address for bean " + clazz.getSimpleName() + ": " + clientIp);
//                // You can log the IP address using your preferred logging framework here
//                break;
//            }
//        }
//    }
//}
