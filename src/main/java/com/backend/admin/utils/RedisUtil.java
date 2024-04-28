package com.backend.admin.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis工具类，提供了一系列操作Redis的方法。
 */

public class RedisUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * RedisUtil构造函数，注入RedisTemplate。
     *
     * @param redisTemplate Redis模板，用于操作Redis。
     */
    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 为指定的键设置值，如果键不存在，则设置失败。
     *
     * @param key 键名
     * @param value 要设置的值
     * @return 如果设置成功返回true，如果键已存在返回false
     */
    public Boolean set(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 根据键获取值。
     *
     * @param key 键名
     * @return 键对应的值，如果键不存在返回null
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除指定的键。
     *
     * @param key 键名
     * @return 如果删除成功返回true，否则返回false
     */
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 为指定的键设置过期时间。
     *
     * @param key 键名
     * @param time 过期时间，单位为秒
     * @return 如果设置成功返回true，否则返回false
     */
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, java.util.concurrent.TimeUnit.SECONDS);
    }

    /**
     * 获取指定键的过期时间。
     *
     * @param key 键名
     * @return 键的过期时间，以秒为单位。如果键没有设置过期时间，返回-2；如果键已过期，返回-1。
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, java.util.concurrent.TimeUnit.SECONDS);
    }

    /**
     * 检查指定的键是否存在。
     *
     * @param key 键名
     * @return 如果键存在返回true，否则返回false
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 为指定键的值增加指定的增量。
     *
     * @param key 键名
     * @param delta 增量值
     * @return 增加后的值
     */
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 为指定键的值减少指定的增量。
     *
     * @param key 键名
     * @param delta 减量值
     * @return 减少后的值
     */
    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    /**
     * 从哈希表中获取指定键的字段值。
     *
     * @param key 键名
     * @param item 字段名
     * @return 字段的值，如果字段不存在返回null
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

}
