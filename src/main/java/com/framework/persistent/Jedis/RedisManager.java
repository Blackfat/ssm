package com.framework.persistent.Jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {
    
    private JedisPool jedisPool;
    
    
    private static class RedisManagerHolder {
        private static final RedisManager instance = new RedisManager();
    }

    public static RedisManager getInstance() {
        return RedisManagerHolder.instance;
    }
    
    private RedisManager() {
        RedisConfig redisConfig = new RedisConfig();
        JedisPoolConfig jedisPoolConfig = getConfig(redisConfig);
        RedisConfig.RedisServer redisServer = redisConfig.getRedisServers().get(0);
        if (redisServer.getPassword() != null) {
            jedisPool = new JedisPool(jedisPoolConfig,
                    redisServer.getIp(),
                    redisServer.getPort(),
                    redisConfig.getConnectionConfig().getTimeOut(),
                    redisServer.getPassword());
        } else {
            jedisPool = new JedisPool(jedisPoolConfig,
                    redisServer.getIp(),
                    redisServer.getPort(),
                    redisConfig.getConnectionConfig().getTimeOut());
        }
    }
    
    private JedisPoolConfig getConfig(RedisConfig redisConfig) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisConfig.getConnectionConfig().getMaxIdel());
        jedisPoolConfig.setMaxTotal(redisConfig.getConnectionConfig().getMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getConnectionConfig().getMaxWait());
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }
    
    public RedisConnection getConnection() {
        return new SingleRedisConnection(jedisPool.getResource());
    }

}
