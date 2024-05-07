package com.bookshop.catalogservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "shop")
public class ShopProperties {

    /**
     * A message to welcome users.
     */
    private String greeting;
}
