package com.demo.controller;

import com.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abs")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/process")
    public ResponseEntity<String> processVideo(@RequestParam String inputPath, @RequestParam String outputPath) {
        videoService.readProcessWrite(inputPath, outputPath);
        return ResponseEntity.ok("Video processed and stored successfully.");
    }
}
