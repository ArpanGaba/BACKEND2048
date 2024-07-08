package com.example.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//Why It's Necessary
//        Cross-Origin Requests:
//
//        Browsers enforce the same-origin policy, which restricts web pages from making requests to a different domain than the one that served the web page. Configuring CORS allows your Netlify-deployed front-end to communicate with your back-end server.
//        Allowed Origins:
//
//        The allowedOrigins method specifies which domains are allowed to make requests to your server. Replace "https://your-site-name.netlify.app" with the actual URL of your Netlify-deployed application.
//        Allowed Methods:
//
//        The allowedMethods method specifies which HTTP methods (GET, POST, PUT, DELETE, OPTIONS) are allowed when making requests to your server.

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("https://your-site-name.netlify.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
}
