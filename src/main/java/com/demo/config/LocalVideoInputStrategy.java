package com.demo.config;
import org.springframework.stereotype.Component;
import java.nio.file.Paths;

@Component
public class LocalVideoInputStrategy implements VideoInputStrategy {
    @Override
    public String getVideoAccessPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().toString();
    }
}
