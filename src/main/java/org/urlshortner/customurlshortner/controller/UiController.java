package org.urlshortner.customurlshortner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.urlshortner.customurlshortner.interfaces.IUrlService;

@Controller
public class UiController {

    private final IUrlService urlService;

    @Value("${shortener.domain}")
    private String domain;

    public UiController(IUrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("longUrl") String longUrl, Model model) {
        String shortCode = urlService.shortenUrl(longUrl);
        String shortUrl = domain + "/" + shortCode;
        model.addAttribute("shortUrl", shortUrl);
        return "index";
    }
}

