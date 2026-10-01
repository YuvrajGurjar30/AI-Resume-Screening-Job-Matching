package com.yuvraj.resume_screening.controller;

import com.yuvraj.resume_screening.entity.Resume;
import com.yuvraj.resume_screening.service.PdfTextExtractionService;
import com.yuvraj.resume_screening.service.ResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    private final PdfTextExtractionService pdfTextExtractionService;
    private final ResumeService resumeService;

    public FileUploadController(
            PdfTextExtractionService pdfTextExtractionService,
            ResumeService resumeService) {
        this.pdfTextExtractionService = pdfTextExtractionService;
        this.resumeService = resumeService;
    }

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

            // Extract text from PDF
            String extractedText = pdfTextExtractionService.extractText(filePath);

            // Save extracted text in database
            Resume resume = new Resume();
            resume.setFileName(file.getOriginalFilename());
            resume.setResumeText(extractedText);

            resumeService.saveResume(resume);

            System.out.println("PDF text saved in database successfully!");

            return "Resume uploaded and text saved successfully!";

        } catch (IOException e) {
            return "Resume upload failed: " + e.getMessage();
        }
    }
}