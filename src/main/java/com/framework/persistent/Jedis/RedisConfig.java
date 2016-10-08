package com.framework.persistent.Jedis;

import java.util.List;

public class RedisConfig {
    
    public static class ConnectionConfig{
        private int maxTotal;
        
        private int maxIdel;
        
        private int timeOut;
        
        private int maxWait;

        public int getMaxTotal() {
            return maxTotal;
        }

        public void setMaxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
        }

        public int getMaxIdel() {
            return maxIdel;
        }

        public void setMaxIdel(int maxIdel) {
            this.maxIdel = maxIdel;
        }

        public int getTimeOut() {
            return timeOut;
        }

        public void setTimeOut(int timeOut) {
            this.timeOut = timeOut;
        }

        public int getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(int maxWait) {
            this.maxWait = maxWait;
        }
    }
    
    public static class RedisServer{
        private String ip;
        
        private int port;
        
        private String password;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
         
    }
    
    private ConnectionConfig connectionConfig;
    
    private List<RedisServer> redisServers;

    public ConnectionConfig getConnectionConfig() {
        return connectionConfig;
    }

    public void setConnectionConfig(ConnectionConfig connectionConfig) {
        this.connectionConfig = connectionConfig;
    }

    public List<RedisServer> getRedisServers() {
        return redisServers;
    }

    public void setRedisServers(List<RedisServer> redisServers) {
        this.redisServers = redisServers;
    }
    
    
    
    
    

}
