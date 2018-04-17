package com.example.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
 
@Component
public class SpringUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext = null;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       if(SpringUtil.applicationContext == null){
           SpringUtil.applicationContext  = applicationContext;
       }
      System.out.println("---------------------------------------------------------------------");
      System.out.println("---------------------------------------------------------------------");
    System.out.println("---------------com.example.utils------------------------------------------------------");
       System.out.println("========ApplicationContext configure successfully SpringUtils.getAppContext() to obtain applicationContext ,applicationContext="+SpringUtil.applicationContext+"========");
      System.out.println("---------------------------------------------------------------------");
    }
   
    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
       return applicationContext;
    }
   
    //通过name获取 Bean.
    public static Object getBean(String name){
       return getApplicationContext().getBean(name);
    }
   
    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
       return getApplicationContext().getBean(clazz);
    }
   
    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
       return getApplicationContext().getBean(name, clazz);
    }
}