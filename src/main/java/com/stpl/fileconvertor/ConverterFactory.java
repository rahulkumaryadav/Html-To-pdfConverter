package com.stpl.fileconvertor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConverterFactory {
    public HtmlToPdf getInstance(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean.xml");
        return (HtmlToPdf) applicationContext.getBean("converter");
    }
}
