package com.framework.persistent.Jedis;

import java.io.Serializable;
import java.util.Set;

public interface RedisConnection {
    
    void close();
    
    Boolean isConnected();
    
    Long hset(String key, String field, Serializable object);
    
    Long del(String... keys);
    
    Object hget(String key, String field);
    
    Long hdel(String key, String... fields);

    Long expire(String key, int seconds);

    Set<String> hkeys(String key);

    Boolean exists(String key);

}
