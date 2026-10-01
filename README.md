# 🤖 AI-Based Resume Screening & Job Matching

An intelligent recruitment support system designed to automate resume processing, extract candidate information, and match candidates with suitable job requirements.

The project is being developed as a final-year B.Tech CSE project using **Spring Boot, MySQL, and AI/NLP-based techniques**.

## 📌 Project Overview

Recruiters often have to review a large number of resumes for a single job opening. Manually screening resumes can be time-consuming and makes it difficult to consistently identify candidates whose skills and experience match a particular job description.

This project aims to build a backend-driven recruitment system that can:

* 📄 Upload and process resumes
* 🔍 Extract useful information from resumes
* 🧠 Analyze candidate skills and qualifications
* 💼 Compare resumes with job descriptions
* 📊 Calculate candidate-job matching scores
* 👨‍💼 Help recruiters shortlist relevant candidates

### Basic Workflow

```text
Resume Upload
     ↓
PDF Text Extraction
     ↓
Resume Data Processing
     ↓
Skill & Information Extraction
     ↓
Job Description Matching
     ↓
Candidate Match Score
     ↓
Recruiter Review
```

---

## 🎯 Objectives

1. Automate the initial resume screening process.
2. Extract relevant information from uploaded resumes.
3. Identify candidate skills, education, and experience.
4. Compare candidate profiles with job requirements.
5. Generate a meaningful job-match score.
6. Provide REST APIs for resume and recruitment operations.
7. Build a scalable backend that can later be connected to a web-based recruiter dashboard.

---

## ✨ Planned Features

### Resume Management

* Upload resume files
* Store resume information
* View all uploaded resumes
* View individual resume details
* Delete resume records

### Resume Processing

* PDF text extraction
* Resume content processing
* Candidate information extraction
* Skill extraction

### Job Matching

* Job description management
* Resume-to-job comparison
* Skill matching
* Candidate-job compatibility score
* Candidate ranking based on matching criteria

### Recruiter Dashboard

* View candidates
* Search and filter candidates
* View matching scores
* Review extracted resume information

---

## 🛠️ Technology Stack

### Backend

* **Java**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**

### Database

* **MySQL**

### Resume Processing

* **Apache PDFBox** — PDF text extraction
* **NLP techniques** — planned

### API Testing

* **Postman**

### Development Tools

* **IntelliJ IDEA**
* **Git**
* **GitHub**
* **Maven**

---

## 🏗️ Current Project Structure

```text
AI-Resume-Screening-Job-Matching/
│
├── README.md
│
└── resume-screening/
    │
    ├── pom.xml
    │
    └── src/
        └── main/
            ├── java/
            │   └── com/
            │       └── yuvraj/
            │           └── resume_screening/
            │               │
            │               ├── ResumeScreeningApplication.java
            │               │
            │               ├── controller/
            │               │   ├── TestController.java
            │               │   ├── ResumeController.java
            │               │   └── FileUploadController.java
            │               │
            │               ├── entity/
            │               │   └── Resume.java
            │               │
            │               ├── repository/
            │               │   └── ResumeRepository.java
            │               │
            │               └── service/
            │                   ├── ResumeService.java
            │                   └── PdfTextExtractionService.java
            │
            └── resources/
                └── application.properties
```

---

## 🚀 Currently Implemented

### ✅ Week 1 — Backend Setup & Resume Upload

The first development phase has been completed.

Implemented:

* Spring Boot backend setup
* MySQL database integration
* Resume entity
* JPA repository
* Service layer
* REST controllers
* Resume CRUD APIs
* PDF resume upload API
* Local resume file storage
* Environment-variable based database password configuration
* API testing using Postman

### ✅ Week 2 — PDF Text Extraction

The second development phase has been completed.

Implemented:

* Apache PDFBox integration
* PDF text extraction from uploaded resumes
* Extracted resume text storage in MySQL
* Database support for large resume text content
* PDF processing tested successfully through Postman

### Available APIs

| Method | Endpoint        | Purpose                            |
| ------ | --------------- | ---------------------------------- |
| GET    | `/`             | Check backend status               |
| POST   | `/resumes`      | Save resume information            |
| GET    | `/resumes`      | Get all resumes                    |
| GET    | `/resumes/{id}` | Get resume by ID                   |
| DELETE | `/resumes/{id}` | Delete resume                      |
| POST   | `/upload`       | Upload resume PDF and extract text |

---

## 📅 Development Roadmap

### Week 1 — Backend Foundation ✅

* [x] Spring Boot project setup
* [x] MySQL integration
* [x] Resume entity
* [x] Repository and service layer
* [x] Resume REST APIs
* [x] PDF upload functionality
* [x] GitHub repository setup

### Week 2 — PDF Text Extraction ✅

* [x] Add PDFBox
* [x] Extract text from uploaded resumes
* [x] Store extracted text in database
* [x] Test PDF processing through Postman

### Week 3 — Resume Information Extraction

* [ ] Extract candidate name
* [ ] Extract email
* [ ] Identify skills
* [ ] Extract education
* [ ] Extract experience information

### Week 4 — Job Description Module

* [ ] Create job entity
* [ ] Add job description APIs
* [ ] Store job requirements
* [ ] Process required skills

### Week 5 — Resume & Job Matching

* [ ] Compare resume skills with job requirements
* [ ] Implement matching logic
* [ ] Generate match percentage
* [ ] Store matching results

### Week 6 — Candidate Ranking

* [ ] Rank candidates according to match score
* [ ] Add filtering and searching
* [ ] Improve matching algorithm

### Week 7+ — Frontend & Final Improvements

* [ ] Recruiter dashboard
* [ ] Candidate management interface
* [ ] Match score visualization
* [ ] UI improvements
* [ ] Testing and bug fixing
* [ ] Documentation
* [ ] Final project presentation preparation

---

## 🔐 Security

Sensitive database credentials are **not stored directly in the source code**.

Database credentials are loaded using environment variables:

```properties
spring.datasource.password=${DB_PASSWORD}
```

This helps prevent sensitive credentials from being exposed through the GitHub repository.

---

## 🧪 API Testing

The backend APIs are tested using **Postman**.

Example:

```http
GET http://localhost:8080/resumes
```

For PDF upload:

```http
POST http://localhost:8080/upload
```

Use **form-data** with:

```text
Key: file
Type: File
Value: Select a PDF resume
```

The uploaded PDF is stored locally, its text is extracted using Apache PDFBox, and the extracted text is saved in the MySQL database.

Example response:

```json
[
  {
    "id": 3,
    "fileName": "test_resume.pdf",
    "candidateName": "Yuvraj Singh",
    "email": "yuvraj@example.com",
    "skills": "Java, Spring Boot, MySQL, React",
    "experience": 1,
    "education": "B.Tech CSE",
    "resumeText": "Java Spring Boot developer with experience in backend development"
  }
]
```

---

## ⚙️ How to Run the Project

### 1. Clone the Repository

```bash
git clone https://github.com/YuvrajGurjar30/AI-Resume-Screening-Job-Matching.git
```

### 2. Open the Project

Open the cloned project in **IntelliJ IDEA**.

### 3. Configure MySQL

Create the database:

```sql
CREATE DATABASE resume_screening;
```

Configure the database connection in:

```text
resume-screening/src/main/resources/application.properties
```

The database password should be provided through the `DB_PASSWORD` environment variable.

### 4. Run the Application

Run:

```text
ResumeScreeningApplication.java
```

The backend will start on:

```text
http://localhost:8080
```

### 5. Test the Backend

Use Postman to test the available REST APIs.

For PDF upload, send a `POST` request to `/upload` using **form-data** with the key `file` and select a PDF resume.

---

## 🔮 Future Scope

The system can be further extended with:

* Advanced NLP-based resume parsing
* Semantic resume-job matching
* Machine learning-based candidate scoring
* Recruiter dashboard
* Candidate search and filtering
* Multiple job postings
* Authentication and role-based access
* Resume analytics
* Cloud storage for resumes
* Deployment using cloud platforms

---

## 👨‍💻 Developer

**Yuvraj Singh**

B.Tech CSE — Artificial Intelligence & Machine Learning / IIOT

GLA University, Mathura

---

## 📌 Project Status

🚧 **Currently under active development**

> Week 1 — Backend foundation and resume upload completed successfully.
> Week 2 — PDF text extraction and database storage completed successfully.

More features will be added incrementally throughout the development cycle.
