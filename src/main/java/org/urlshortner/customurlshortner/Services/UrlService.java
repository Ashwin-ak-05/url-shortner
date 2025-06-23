package org.urlshortner.customurlshortner.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.urlshortner.customurlshortner.Utility.Base62Encoder;
import org.urlshortner.customurlshortner.interfaces.IRedisService;
import org.urlshortner.customurlshortner.interfaces.IUrlService;

@Service
public class UrlService implements IUrlService {
    private static final String COUNTER_PREFIX = "url-counter:";

    @Value("${machine.id}")
    private String machineId;

    @Value("${shortener.domain}")
    private String domain;

    private RedisImplementationService redisImplementationService;

    public UrlService(RedisImplementationService redisImplementationService) {
        this.redisImplementationService = redisImplementationService;
    }

    @Override
    public String shortenUrl(String url) {

        String counterKey = COUNTER_PREFIX + machineId;
        //Long counter = redisTemplate.opsForValue().increment(counterKey);
        Long counter = redisImplementationService.getCounter(counterKey);

        //String base62 = encoder.encode(counter);
        String base62 = Base62Encoder.Base62Encode(counter);
        while (base62.length() < 6) {
            base62 = "0" + base62;
        }

        String shortCode = machineId + base62;
        redisImplementationService.saveUrl(url, shortCode);
        //redisTemplate.opsForValue().set(url, shortCode);

        return shortCode;
    }

    @Override
    public String getLongUrl(String longUrl) {
        return "";
    }


}
