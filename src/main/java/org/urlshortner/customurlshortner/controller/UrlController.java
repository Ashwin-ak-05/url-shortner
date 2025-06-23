package org.urlshortner.customurlshortner.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.urlshortner.customurlshortner.Services.RedisImplementationService;
import org.urlshortner.customurlshortner.dto.LongUrlRequest;
import org.urlshortner.customurlshortner.dto.ShorternUrlRequest;
import org.urlshortner.customurlshortner.interfaces.DatabaseInterface;
import org.urlshortner.customurlshortner.interfaces.IUrlService;

@RestController
@Tag(name = "URL Shortener", description = "Generate a shortened URL")
public class UrlController {

    @Value("${shortener.domain}")
    private String domain;

    DatabaseInterface service;

    IUrlService urlService;


    public UrlController(DatabaseInterface databaseInterface, IUrlService urlService) {
        this.service = databaseInterface;
        this.urlService = urlService;
    }

    @Hidden
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @Operation(
            summary = "Shorten a long URL",
            description = "Accepts a long URL and returns a shortened version."
    )
    @PostMapping("/getshorturl")
    public String getShortUrl(@RequestBody ShorternUrlRequest request) {
        String shortCode = urlService.shortenUrl(request.getLongUrl());
        return domain + "/" + shortCode;
    }


    @Hidden
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode) {
        String originalUrl = service.getUrl(shortCode);
        if (originalUrl != null) {
            return ResponseEntity.status(302)
                    .header("Location", originalUrl)
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Hidden
    @GetMapping("/test")
    public ResponseEntity<String> testRedisConnection() throws RedisConnectionFailureException {
        String response = service.testConnection();
        return ResponseEntity.ok(response);
    }

}
