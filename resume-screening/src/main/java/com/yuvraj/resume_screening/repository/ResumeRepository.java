package com.yuvraj.resume_screening.repository;

import com.yuvraj.resume_screening.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}