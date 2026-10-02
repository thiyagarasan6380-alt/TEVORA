# TEVORA

## Student Project Collaboration Hub

TEVORA is a web-based platform that helps students find teammates for their academic projects based on required skills and interests.

Students can create projects, discover available projects, apply to join teams, and manage project members.

---

## Features

- Student login
- Create projects
- View available projects
- Search projects
- Edit projects
- Delete projects
- Open/Close project status
- Apply to join projects
- View applications
- Accept student applications
- View accepted team members
- Store project and application data in MySQL

---

## Technology Stack

### Frontend
- React
- Tailwind CSS
- JavaScript

### Backend
- Java
- Spring Boot
- Spring Data JPA

### Database
- MySQL

### Tools
- Visual Studio Code
- Git
- GitHub
- Postman

---

## Project Structure

```text
TEVORA/
│
├── frontend/
│   └── react-master/
│
├── backend/
│   └── studenthub/
│
├── .gitignore
└── README.md

How It Works
Student
   ↓
Login
   ↓
Browse Projects
   ↓
Create / View Project
   ↓
Apply to Join
   ↓
Project Owner Reviews Application
   ↓
Accept Application
   ↓
Team Member Added
   ↓
Project Collaboration


Main Backend APIs
Users
POST /users/register
POST /users/login
GET  /users/{username}
GET  /users/id/{id}
Projects
GET    /projects
POST   /projects
PUT    /projects/{id}
DELETE /projects/{id}
Applications
POST /applications
GET  /applications
GET  /applications/project/{projectId}
GET  /applications/student/{studentId}
GET  /applications/accepted
PUT  /applications/accept/{id}

Database
The application uses MySQL to store:
Users
Projects
Applications
Project-related information
Running the Project
1. Start MySQL
Make sure MySQL is running and the required database is available.
2. Start the Backend
Open the Spring Boot project:
backend/studenthub
Run the Spring Boot application.
The backend runs on:
http://localhost:8080
3. Start the Frontend
Open:
frontend/react-master
Install dependencies:
npm install
Start the development server:
npm run dev
The frontend normally runs on:
http://localhost:5173
Security
Database configuration containing local credentials is excluded from GitHub using .gitignore.
Do not commit passwords or other sensitive credentials to the repository.
Project Status
Completed
TEVORA currently supports the complete basic workflow from student login and project creation to applications and team formation.
Author
Thiyagarasan C
B.E. Computer Science and Engineering
Loyola-ICAM College of Engineering and Technology (LICET)

### Then save it

After saving, run:

```powershell
git add .
git status
