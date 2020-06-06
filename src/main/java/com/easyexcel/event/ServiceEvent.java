package com.easyexcel.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author Mr丶s
 * @date 2019/8/8 11:12
 * @description
 */
@Service
public class ServiceEvent {

    @Autowired
    private ApplicationContext applicationContext;

    public void register(String sender){
        applicationContext.publishEvent(new RegisterEvent(this,sender));
    }

}
