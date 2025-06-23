package org.urlshortner.customurlshortner.interfaces;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.urlshortner.customurlshortner.exceptions.RedisConnectionFailedException;

public interface DatabaseInterface {
    public String getUrl(String shortUrl);
    public String saveShortUrl(String url, String shortUrl);
    public String testConnection() throws RedisConnectionFailureException;
    public String saveUrl(String url, String shortUrl);
}
