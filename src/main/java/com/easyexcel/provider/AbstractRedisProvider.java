package com.easyexcel.provider;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr丶s
 * @date 2020/3/19 2:38 下午
 * @description 父类
 */
public abstract class AbstractRedisProvider {

    /**
     * 获取yml中定义的过期时间
     */
    @Value("${spring.redis.expire}")
    Integer expire;

    /**
     * 获取Spring容器中的redisTemplate
     */
    @Qualifier("redisTemplate")
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 保存值
     *
     * @param key
     * @param object
     * @return
     */
    abstract boolean save(String key, Object object);

    abstract boolean save(String key, Object object, long expire);

    abstract boolean save(String key, Object object, long expire, TimeUnit timeUnit);

    /**
     * 存入长期有效
     *
     * @param key
     * @param object
     * @return
     */
    abstract boolean savePermanent(String key, Object object);


    /**
     * 根据key获取对象
     *
     * @param key
     * @return
     */
    abstract Object get(String key);


    /**
     * 根据key删除对象
     *
     * @param key key
     * @return
     */
    abstract boolean remove(String key);

    /**
     * 删除一组符合规则的redis数据（前缀匹配）
     *
     * @param pattern 匹配规则
     * @return
     */
    abstract boolean prefixBatchRemove(String pattern);

    /**
     * 删除一组符合规则的redis数据（后缀匹配）
     *
     * @param pattern 匹配规则
     * @return
     */
    abstract boolean suffixBatchRemove(String pattern);

    /**
     * 删除一组符合规则的redis数据(全匹配)
     *
     * @param pattern 匹配规则
     * @return
     */
    abstract boolean perfectBatchRemove(String pattern);


    /**
     * 增加值
     *
     * @param key   key
     * @param value 需要增加的值
     * @return
     */
    abstract void incrementValue(String key, long value);

    abstract void incrementValue(String key, double value);

    /**
     * 获取key 的过期时间
     *
     * @param key key
     * @return 过期时间
     */
    abstract Long getExpire(String key);

    /**
     * 更新过期时间
     *
     * @param key    key
     * @param expire 新的过期时间
     */
    abstract void updateExpire(String key, long expire);

    abstract void updateExpire(String key, long expire, TimeUnit timeUnit);

    /**
     * 获取key 的过期时间
     *
     * @param key      key
     * @param timeUnit 时间单位
     * @return 过期时间
     */
    abstract Long getExpire(String key, TimeUnit timeUnit);

    /**
     * 是否包含该key
     *
     * @param key key
     * @return 包含 true，不包含 false
     */
    abstract boolean hasKey(String key);
}