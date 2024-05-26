package com.demo.config;

import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

@Component
public class LocalVideoOutputStrategy implements VideoOutputStrategy {
    @Override
    public String getVideoWritePath(String filePath) {
        Path path = Paths.get(filePath).toAbsolutePath();
        try {
            Files.createDirectories(path.getParent());
        } catch (Exception e) {
            throw new RuntimeException("Error preparing local file path", e);
        }
        return path.toString();
    }
}
