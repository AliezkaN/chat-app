package org.aliezkan.chatapi.configuration.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(value = "app.security", ignoreUnknownFields = false)
public class SecurityProperties {

    private Auth auth = new Auth();
    private Jwt jwt = new Jwt();

    @Data
    public static class Jwt {
        @NotNull
        private String secretKey;
        private long expiration = 86400000; // 1 Day
        private long refreshExpiration = 604800000; // 7 Days
    }

    @Data
    public static class Auth {
        @NotNull
        private String[] whiteList = new String[] {};
    }
}
