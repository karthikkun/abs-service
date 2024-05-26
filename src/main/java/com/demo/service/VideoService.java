package com.demo.service;

import com.demo.config.VideoInputStrategy;
import com.demo.config.VideoOutputStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    @Autowired
    private VideoInputStrategy videoInputStrategy;
    @Autowired
    private VideoOutputStrategy videoOutputStrategy;

    @Autowired
    private Backend backend;

    public void readProcessWrite(String inputPath, String outputPath) {
        String inputVideoPath = videoInputStrategy.getVideoAccessPath(inputPath);
        String outputVideoPath = videoOutputStrategy.getVideoWritePath(outputPath);
        backend.processVideo(inputVideoPath, outputVideoPath);
    }
}
