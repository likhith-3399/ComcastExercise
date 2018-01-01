//package com.example.demo;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomizationBean implements EmbeddedServletContainerCustomizer {
//  
//	@Override
//    public void customize(ConfigurableEmbeddedServletContainer container) {
//        container.setContextPath("/serviceapp");       
//        container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
//        container.addErrorPages(new ErrorPage("/errorHeaven"));
//    }
//}