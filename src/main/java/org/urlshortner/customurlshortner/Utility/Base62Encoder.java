package org.urlshortner.customurlshortner.Utility;

import org.springframework.stereotype.Component;

@Component
public class Base62Encoder {
    public static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String Base62Encode(long num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) {
            return "0";
        }
        while (num > 0) {
            int remainder = (int)(num % 62);
            sb.append(CHARSET.charAt(remainder));
            num /= 62;
        }
        return sb.reverse().toString();
    }
}
