package org.urlshortner.customurlshortner.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.urlshortner.customurlshortner.interfaces.DatabaseInterface;
import org.urlshortner.customurlshortner.interfaces.IRedisService;
import org.urlshortner.customurlshortner.interfaces.IUrlService;

@Service
public class RedisImplementationService implements DatabaseInterface, IRedisService {
    private static final String REDIS_KEY_PREFIX = "shorturl:";
    private RedisTemplate<String, String> redisTemplate;
    private IUrlService urlService;
    @Value("${shortener.domain}")
    private String domain;

    public RedisImplementationService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String getUrl(String shortCode) {
        return redisTemplate.opsForValue().get(REDIS_KEY_PREFIX + shortCode);
    }

    @Override
    public String saveShortUrl(String url, String shortUrl) {
        return "";
    }

    @Override
    public String testConnection() throws RedisConnectionFailureException {
        try {
            // Attempt Redis connection (pseudo-code)
            redisTemplate.opsForValue().get("test");
            return "Connected";
        } catch (Exception e) {
            throw new RedisConnectionFailureException("Failed to connect to Redis");
        }
    }

    @Override
    public String saveUrl(String longUrl, String shortCode) {
         redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + shortCode, longUrl);
         return shortCode;
    }

    @Override
    public Long getCounter(String key) {
        return redisTemplate.opsForValue().increment(key);
    }
}
