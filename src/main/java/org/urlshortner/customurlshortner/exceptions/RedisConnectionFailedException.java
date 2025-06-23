package org.urlshortner.customurlshortner.exceptions;


public class RedisConnectionFailedException extends Exception {
    public RedisConnectionFailedException(String message) {
        super(message);
    }
}
