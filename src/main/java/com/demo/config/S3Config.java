package com.demo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${app.input.s3.region}")
    private String inputRegion;

    @Value("${app.input.s3.accessKeyId}")
    private String inputAccessKeyId;

    @Value("${app.input.s3.secretAccessKey}")
    private String inputSecretAccessKey;

    @Value("${app.input.s3.bucket}")
    private String inputBucket;

    @Value("${app.output.s3.region}")
    private String outputRegion;

    @Value("${app.output.s3.accessKeyId}")
    private String outputAccessKeyId;

    @Value("${app.output.s3.secretAccessKey}")
    private String outputSecretAccessKey;

    @Value("${app.output.s3.bucket}")
    private String outputBucket;

    @Value("${app.input}")
    private String inputType;

    @Value("${app.output}")
    private String outputType;

    private AmazonS3 buildS3Client(String accessKeyId, String secretAccessKey, String region) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(region)
                .build();
        return s3Client;
    }

    @Bean
    public AmazonS3 inputS3Client() {
        if ("s3".equals(inputType)) {
            return buildS3Client(inputAccessKeyId, inputSecretAccessKey, inputRegion);
        }
        return null;
    }

    @Bean
    public AmazonS3 outputS3Client() {
        if ("s3".equals(outputType)) {
            return buildS3Client(outputAccessKeyId, outputSecretAccessKey, outputRegion);
        }
        return null;
    }
}
