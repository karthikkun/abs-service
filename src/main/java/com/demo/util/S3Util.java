package com.demo.util;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import java.net.URI;
import java.net.URL;

public class S3Util {
    public static URL getPreSignedUrl(AmazonS3 s3Client, String s3Uri, HttpMethod httpMethod) {
        try {
            URI uri = new URI(s3Uri);
            String bucket = uri.getHost();
            String key = uri.getPath().substring(1);
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60;
            expiration.setTime(expTimeMillis);
            System.out.println("Generating pre-signed URL.");
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, key)
                    .withMethod(httpMethod)
                    .withExpiration(expiration);
            return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
