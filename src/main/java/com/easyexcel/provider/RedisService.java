package com.easyexcel.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr丶s
 * @date 2020/3/19 2:38 下午
 * @description redis公用工具类
 */
@Service
@Component
@Slf4j
public class RedisService extends AbstractRedisProvider {

    @Override
    public boolean save(String key, Object object) {
        return save(key, object, expire);
    }

    @Override
    public boolean save(String key, Object object, long expire) {
        return save(key, object, expire, TimeUnit.SECONDS);
    }

    @Override
    public boolean save(String key, Object object, long expire, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, object, expire, timeUnit);
            // 保存完了再判断一下是否保存成功
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("【系统异常】redis保存出错。" + e.getMessage());
            throw new RuntimeException("redis储存错误");
        }
    }

    @Override
    public boolean savePermanent(String key, Object object) {
        try {
            redisTemplate.opsForValue().set(key, object);
            // 保存完了再判断一下是否保存成功
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("【系统异常】redis保存出错。" + e.getMessage());
            throw new RuntimeException("redis储存错误");
        }
    }


    @Override
    public Object get(String key) {
        if (redisTemplate.hasKey(key)) {
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }


    @Override
    public boolean remove(String key) {
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            return get(key) == null;
        }
        return false;
    }

    @Override
    public boolean prefixBatchRemove(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern + "*");
        return delete(keys);
    }

    @Override
    public boolean suffixBatchRemove(String pattern) {
        Set<String> keys = redisTemplate.keys("*" + pattern);
        return delete(keys);
    }

    @Override
    public boolean perfectBatchRemove(String pattern) {
        Set<String> keys = redisTemplate.keys("*" + pattern + "*");
        return delete(keys);
    }

    /**
     * 删除通用部分
     *
     * @param keys
     * @return
     */
    private boolean delete(Set<String> keys) {
        if (keys != null && keys.size() != 0) {
            // 执行删除操作，delete方法返回执行成功的条数
            redisTemplate.delete(keys);
            return true;
        }
        return false;
    }

    @Override
    public void incrementValue(String key, long value) {
        redisTemplate.opsForValue().increment(key, value);
    }

    @Override
    public void incrementValue(String key, double value) {
        redisTemplate.opsForValue().increment(key, value);
    }

    @Override
    public Long getExpire(String key) {
        return getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public void updateExpire(String key, long expire) {
        updateExpire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public void updateExpire(String key, long expire, TimeUnit timeUnit) {
        redisTemplate.expire(key, expire, timeUnit);
    }

    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
