package com.yuvraj.resume_screening.service;

import java.util.List;
import com.yuvraj.resume_screening.entity.Resume;
import com.yuvraj.resume_screening.repository.ResumeRepository;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }
    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }
    public Resume getResumeById(Long id) {
        return resumeRepository.findById(id).orElse(null);
    }
    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}