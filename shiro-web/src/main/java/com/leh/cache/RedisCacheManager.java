package com.leh.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;

/**
 * @Auther: leh
 * @Date: 2019/10/16 12:33
 * @Description:自定义缓存-redis实现
 */
public class RedisCacheManager implements CacheManager{

    @Resource
    private RedisCache redisCache;

    /**
     * s 代表cacheName -- 可以将cacheName 和 redisCache 作为本地缓存放入到map中
     * @param s
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisCache;
    }
}
