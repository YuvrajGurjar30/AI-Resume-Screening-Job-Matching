package com.yuvraj.resume_screening.service;

import com.yuvraj.resume_screening.entity.Resume;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResumeInformationExtractionService {

    public void extractInformation(Resume resume) {

        String text = resume.getResumeText();

        if (text == null || text.isBlank()) {
            return;
        }

        // Extract email
        Pattern emailPattern = Pattern.compile(
                "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
        );

        Matcher emailMatcher = emailPattern.matcher(text);

        if (emailMatcher.find()) {
            resume.setEmail(emailMatcher.group());
        }

        // Extract candidate name from the first line
        String[] lines = text.split("\\R");

        for (String line : lines) {
            String candidate = line.trim();

            if (candidate.isEmpty()) {
                continue;
            }

            // Skip common headings
            if (candidate.equalsIgnoreCase("resume")
                    || candidate.equalsIgnoreCase("curriculum vitae")) {
                continue;
            }

            // Accept a simple name-like first line
            if (candidate.matches(
                    "(?i)[A-Z][A-Z .'-]{1,59}")
                    && !candidate.contains("@")
                    && !candidate.contains("|")) {

                resume.setCandidateName(candidate);
            }

            break;
        }

        // Extract education
        Pattern educationPattern = Pattern.compile(
                "(?i)(B\\.Tech[^\\r\\n]*|Bachelor of[^\\r\\n]*|"
                        + "M\\.Tech[^\\r\\n]*|Master of[^\\r\\n]*|"
                        + "BCA[^\\r\\n]*|MCA[^\\r\\n]*)"
        );

        Matcher educationMatcher = educationPattern.matcher(text);

        if (educationMatcher.find()) {
            resume.setEducation(educationMatcher.group().trim());
        }


        // Extract experience duration in years
        Pattern experiencePattern = Pattern.compile(
                "(?i)(\\d+)\\+?\\s*(years?|yrs?)\\b"
        );

        Matcher experienceMatcher = experiencePattern.matcher(text);

        if (experienceMatcher.find()) {
            resume.setExperience(
                    Integer.parseInt(experienceMatcher.group(1))
            );
        }


        // Extract the Experience section details
        Pattern experienceDetailsPattern = Pattern.compile(
                "(?im)^\\h*EXPERIENCE\\h*\\R([\\s\\S]*?)(?=^\\h*(?:CERTIFICATIONS|EDUCATION|PROJECTS)\\h*$|\\z)"
        );

        Matcher experienceDetailsMatcher =
                experienceDetailsPattern.matcher(text);

        if (experienceDetailsMatcher.find()) {
            String details = experienceDetailsMatcher.group(1).trim();

            if (!details.isEmpty()) {
                resume.setExperienceDetails(details);
            }
        }



        // Extract skills using keywords
        String[] skillKeywords = {
                "Java", "Python", "JavaScript", "React",
                "Spring Boot", "MySQL", "SQL", "HTML",
                "CSS", "REST API", "Machine Learning",
                "OpenCV", "NLP", "Flask", "FastAPI"
        };

        StringBuilder foundSkills = new StringBuilder();

        for (String skill : skillKeywords) {
            Pattern skillPattern = Pattern.compile(
                    "\\b" + Pattern.quote(skill) + "\\b",
                    Pattern.CASE_INSENSITIVE
            );

            if (skillPattern.matcher(text).find()) {
                if (foundSkills.length() > 0) {
                    foundSkills.append(", ");
                }

                foundSkills.append(skill);
            }
        }

        if (foundSkills.length() > 0) {
            resume.setSkills(foundSkills.toString());
        }
    }
}
