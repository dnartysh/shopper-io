package ru.shopper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class AppWebMvcConfigurer implements WebMvcConfigurer {
    @Value("${shopper.user-img-path}")
    private String userImgPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(userImgPath + "**")
                .addResourceLocations("file://" + AppConstants.PROJECT_PATH + userImgPath);
    }
}
