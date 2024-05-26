package com.demo.config;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.kafka.model.S3;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.demo.util.S3Util;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.net.URL;

@Component("s3VideoInputStrategy")
public class S3VideoInputStrategy implements VideoInputStrategy {

    @Autowired(required = false)
    private AmazonS3 s3InputClient;

    @Override
    public String getVideoAccessPath(String s3Uri) {
        if (s3InputClient == null) {
            throw new IllegalStateException("S3 input client is not configured.");
        }
        URL preSignedUrl = S3Util.getPreSignedUrl(s3InputClient, s3Uri, HttpMethod.GET);
        if (preSignedUrl == null) return null; // exception handling
        return preSignedUrl.toString();
    }
}