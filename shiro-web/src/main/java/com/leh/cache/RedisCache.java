package com.leh.cache;

import com.leh.util.JedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * @Auther: leh
 * @Date: 2019/10/16 12:34
 * @Description:自定义缓存
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    private final String SHIRO_CACHE_PREFIX = "shiro-cache:";
    @Resource
    private JedisUtil jedisUtil;

    private byte[] getKey(K k) {
        if (k instanceof String) {
            return (SHIRO_CACHE_PREFIX + k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    /**

     * 实际项目没有必要每次都从redis中读取，可以添加本地的二级缓存
     * 直接从二级缓存中（内存）读取，进一步提升性能
     * 当本地缓存不存在时再从redis中读取，读到之后再写入到二级缓存
     * @param k
     * @return
     * @throws CacheException
     */
    public V get(K k) throws CacheException {
        System.out.println("从redis中获取数据");
        byte[] value = jedisUtil.get(getKey(k));
        if (value == null) {
            return null;
        }
        return (V) SerializationUtils.deserialize(value);
    }

    public V put(K k, V v) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = SerializationUtils.serialize(v);
        jedisUtil.set(key, value);
        jedisUtil.expire(key, 60 * 60 * 60);
        return v;
    }

    public V remove(K k) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = jedisUtil.get(key);
        jedisUtil.del(key);
        if (value != null) {
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    public void clear() throws CacheException {

    }

    public int size() {
        return 0;
    }

    public Set<K> keys() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }
}
