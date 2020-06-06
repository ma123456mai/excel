package com.easyexcel.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

/**
 * @author Mr丶s
 * @date 2019/8/8 9:42
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterEvent extends ApplicationEvent {

    /**
     * 消息key
     */
    private String key;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public RegisterEvent(Object source, String key) {
        super(key);
        this.key = key;
    }

}
