package com.TB.base.quartz;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("simpleService")  
public class SimpleService implements Serializable{  
      
    private static final long serialVersionUID = 122323233244334343L;  
    private static final Logger logger = Logger.getLogger(SimpleService.class);  
      
    public void testMethod(String triggerName){  
        //这里执行定时调度业务  
        logger.info(triggerName);  
    }  
      
    public void testMethod2(){  
        logger.info("testMethod2");  
    }  
}  