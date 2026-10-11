package com.yuvraj.resume_screening.controller;

import com.yuvraj.resume_screening.entity.Resume;
import com.yuvraj.resume_screening.service.PdfTextExtractionService;
import com.yuvraj.resume_screening.service.ResumeInformationExtractionService;
import com.yuvraj.resume_screening.service.ResumeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    private final PdfTextExtractionService pdfTextExtractionService;
    private final ResumeInformationExtractionService informationExtractionService;
    private final ResumeService resumeService;

    public FileUploadController(
            PdfTextExtractionService pdfTextExtractionService,
            ResumeInformationExtractionService informationExtractionService,
            ResumeService resumeService) {
        this.pdfTextExtractionService = pdfTextExtractionService;
        this.informationExtractionService = informationExtractionService;
        this.resumeService = resumeService;
    }

    @PostMapping("/upload")
    public String uploadResume(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "Please select a PDF file.";
        }

        if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            return "Only PDF files are allowed.";
        }

        try {
            String uploadDirectory =
                    System.getProperty("user.dir") + "/uploads/";

            File directory = new File(uploadDirectory);

            if (!directory.exists() && !directory.mkdirs()) {
                return "Unable to create upload directory.";
            }

            String safeFileName = UUID.randomUUID() + ".pdf";
            File destination = new File(directory, safeFileName);

            file.transferTo(destination);

            String extractedText =
                    pdfTextExtractionService.extractText(
                            destination.getAbsolutePath());

            Resume resume = new Resume();
            resume.setFileName(file.getOriginalFilename());
            resume.setResumeText(extractedText);

            // Extract email and skills from resume text
            informationExtractionService.extractInformation(resume);

            // Save extracted information in MySQL
            resumeService.saveResume(resume);

            return "Resume uploaded successfully! Email and skills extraction completed.";

        } catch (IOException e) {
            return "Resume upload failed: " + e.getMessage();
        }
    }
}
