package com.yuvraj.resume_screening.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public String uploadResume(@RequestParam("file") MultipartFile file) {

        try {
            String uploadDirectory = System.getProperty("user.dir") + "/uploads/";

            File directory = new File(uploadDirectory);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = uploadDirectory + file.getOriginalFilename();

            file.transferTo(new File(filePath));

            return "Resume uploaded successfully!";

        } catch (IOException e) {
            return "Resume upload failed: " + e.getMessage();
        }
    }
}