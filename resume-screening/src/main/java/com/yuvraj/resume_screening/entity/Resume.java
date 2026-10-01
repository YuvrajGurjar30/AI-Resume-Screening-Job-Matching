package com.yuvraj.resume_screening.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String candidateName;
    private String email;
    private String skills;
    private int experience;
    private String education;
    @jakarta.persistence.Column(columnDefinition = "TEXT")
    private String resumeText;
}