package org.urlshortner.customurlshortner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

//@Configuration
//public class RedisConfig {
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//        config.setHostName("singapore-keyvalue.render.com");
//        config.setPort(6379);
//        config.setPassword(RedisPassword.of("qMac1RQ2Ac1IafD4FBIXIEAiKQcQeSXH"));
//
//        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                .useSsl()  // Enable SSL
//                .build();
//
//        return new LettuceConnectionFactory(config, clientConfig);
//    }
//}
