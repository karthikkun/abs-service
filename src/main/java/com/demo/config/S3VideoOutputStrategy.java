package com.demo.config;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.demo.util.S3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component("s3VideoOutputStrategy")
public class S3VideoOutputStrategy implements VideoOutputStrategy {

    @Autowired(required = false)
    private AmazonS3 s3OutputClient;

    @Override
    public String getVideoWritePath(String s3Uri) {
        if (s3OutputClient == null) {
            throw new IllegalStateException("S3 output client is not configured.");
        }
        URL preSignedUrl = S3Util.getPreSignedUrl(s3OutputClient, s3Uri, HttpMethod.PUT);
        if (preSignedUrl == null) return null; // exception handling
        return preSignedUrl.toString();
    }
}