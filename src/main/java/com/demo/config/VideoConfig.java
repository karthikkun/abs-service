package com.demo.config;

import com.demo.annotations.conditional.LocalInputCondition;
import com.demo.annotations.conditional.LocalOutputCondition;
import com.demo.annotations.conditional.S3InputCondition;
import com.demo.annotations.conditional.S3OutputCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoConfig {

    @Bean
    @Conditional(S3InputCondition.class)
    public VideoInputStrategy s3VideoInputStrategy() {
        return new S3VideoInputStrategy();
    }

    @Bean
    @Conditional(LocalInputCondition.class)
    public VideoInputStrategy localVideoInputStrategy() {
        return new LocalVideoInputStrategy();
    }

    @Bean
    @Conditional(S3OutputCondition.class)
    public VideoOutputStrategy s3VideoOutputStrategy() {
        return new S3VideoOutputStrategy();
    }

    @Bean
    @Conditional(LocalOutputCondition.class)
    public VideoOutputStrategy localVideoOutputStrategy() {
        return new LocalVideoOutputStrategy();
    }
}

