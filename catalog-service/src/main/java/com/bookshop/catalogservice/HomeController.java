package com.bookshop.catalogservice;

import com.bookshop.catalogservice.config.ShopProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final ShopProperties shopProperties;

    public HomeController(ShopProperties shopProperties) {
        this.shopProperties = shopProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return shopProperties.getGreeting();
    }
}
