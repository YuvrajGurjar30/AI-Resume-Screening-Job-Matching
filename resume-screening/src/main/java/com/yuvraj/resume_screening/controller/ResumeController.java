package com.yuvraj.resume_screening.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.yuvraj.resume_screening.entity.Resume;
import com.yuvraj.resume_screening.service.ResumeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/resumes")
    public Resume saveResume(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }
    @GetMapping("/resumes")
    public List<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }
    @GetMapping("/resumes/{id}")
    public Resume getResumeById(@PathVariable Long id) {
        return resumeService.getResumeById(id);
    }
    @DeleteMapping("/resumes/{id}")
    public String deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return "Resume deleted successfully!";
    }
}