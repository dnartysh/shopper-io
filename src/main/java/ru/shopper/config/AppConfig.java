package ru.shopper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class AppConfig {

    @Value(("${shopper.user-img-path}"))
    private String userImgPath;

    @PostConstruct
    public void addUserImgDirIfNotExists() {
        Path destFile = Path.of(AppConstants.PROJECT_PATH + userImgPath + "/");

        if (!Files.exists(destFile)) {
            File srcFile = new File(AppConstants.RESOURCES_PATH + "/static/img/user-not-found.png");

            if (srcFile.exists()) {
                try {
                    Files.createDirectories(destFile);
                    Files.copy
                            (srcFile.toPath(),
                                    Path.of(destFile + "/" + srcFile.getName()),
                                    StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Создан каталог фотографий пользователей: " + destFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
 }
