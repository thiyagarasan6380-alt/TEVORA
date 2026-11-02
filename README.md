 <div align="center">

![TEVORA](https://img.shields.io/badge/TEVORA-Student_Project_Collaboration_Hub-000000?style=for-the-badge&labelColor=FF9933)

![Status](https://img.shields.io/badge/Status-Completed-FF9933?style=flat-square&labelColor=000000)
![Frontend](https://img.shields.io/badge/Frontend-React-FF9933?style=flat-square&labelColor=000000&logo=react&logoColor=FF9933)
![Backend](https://img.shields.io/badge/Backend-Spring_Boot-FF9933?style=flat-square&labelColor=000000&logo=springboot&logoColor=FF9933)
![Database](https://img.shields.io/badge/Database-MySQL-FF9933?style=flat-square&labelColor=000000&logo=mysql&logoColor=FF9933)

</div>

---

# 🟧 TEVORA

> **Find teammates for your academic projects based on the skills you need.**

TEVORA is a Student Project Collaboration Hub where students can create projects, discover opportunities, apply to join teams, and manage project collaboration.

It brings project owners and students together through a simple project and application management workflow.

## 🟧 Features

| For Students | For Project Owners |
|---|---|
| 🔐 Student login | ➕ Create projects |
| 🔍 Browse available projects | ✏️ Edit projects |
| 📝 Apply to join projects | 🗑️ Delete projects |
| 📄 Track application status | 🔓 Open or close applications |
| 👥 View accepted team members | 📬 View received applications |
| | ✅ Accept student applications |
| | 👨‍👩‍👧‍👦 Build project teams |

💾 Project, student, application, and team information is stored in MySQL.

## 🟧 How It Works

```text
                 Student Login
                       |
                 Browse Projects
                       |
               +-------+-------+
               |               |
         Create Project   Apply to Project
               |               |
         Manage Project   Owner Reviews
                               |
                       Accept Application
                               |
                       Team Formation
                               |
                         Collaboration 🚀
```

## 🟧 Technology Stack

| Layer | Technologies |
|---|---|
| **Frontend** | React, JavaScript, Tailwind CSS |
| **Backend** | Java 21, Spring Boot |
| **Database** | MySQL 8, Spring Data JPA |
| **Build Tools** | Maven, npm |
| **Development Tools** | VS Code, Git, GitHub, Postman |

## 🟧 Project Structure

You Can See the actual full Diagram : (https://gitdiagram.com/thiyagarasan6380-alt/tevora)

```text
TEVORA/
│
├── backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       └── resources/
│   │           └── application.properties
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── frontend/
│   ├── src/
│   ├── public/
│   ├── package.json
│   └── package-lock.json
│
├── database/
│   ├── tevora_Schema.sql
│   ├── tevora_sample_data.sql
│   └── README.md
│
├── .gitignore
└── README.md
```

## 🟧 Main Backend APIs

### 👤 Users

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/users/register` | Register a student through the API |
| `POST` | `/users/login` | Authenticate a student |
| `GET` | `/users/id/{id}` | Retrieve student information by user ID |

### 📁 Projects

| Method | Endpoint | Purpose |
|---|---|---|
| `GET` | `/projects` | Retrieve available projects |
| `POST` | `/projects` | Create a project |
| `PUT` | `/projects/{id}` | Update a project |
| `DELETE` | `/projects/{id}` | Delete a project |

### 📨 Applications

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/applications` | Apply to a project |
| `GET` | `/applications` | Retrieve applications |
| `GET` | `/applications/project/{projectId}` | Retrieve applications for a project |
| `GET` | `/applications/student/{studentId}` | Retrieve applications by a student |
| `PUT` | `/applications/accept/{id}` | Accept an application |

### 👥 Teams

| Method | Endpoint | Purpose |
|---|---|---|
| `GET` | `/teams/{teamId}/members` | Retrieve members of a team |
| `GET` | `/teams/project/{projectId}/members` | Retrieve team members by project |

## 🟧 Database Design

TEVORA uses **nine MySQL tables** to manage application data and relationships.

| Table | Purpose |
|---|---|
| `users` | Stores login credentials and roles |
| `students` | Stores student information |
| `projects` | Stores project details |
| `applications` | Stores project applications |
| `skills` | Stores available skills |
| `project_skills` | Connects projects with skills |
| `student_skills` | Connects students with skills |
| `teams` | Stores project teams |
| `team_members` | Stores team membership details |

**Database features:**
- Primary keys
- Foreign key relationships
- Unique constraints
- Cascade delete relationships

## 🟧 Installation and Setup

### Requirements

- Java JDK 21
- MySQL Server 8.0 or later
- MySQL Workbench
- Node.js and npm
- Git

### 1. Clone the Repository

```bash
git clone https://github.com/thiyagarasan6380-alt/TEVORA.git
cd TEVORA
```

### 2. Set Up MySQL Database

1. Open MySQL Workbench.
2. Connect to your local MySQL server.
3. Open `database/tevora_Schema.sql`.
4. Execute the complete SQL script.
5. Open `database/tevora_sample_data.sql`.
6. Execute it to insert the starter skills.

This creates the `tevora_db` database, its nine tables, and starter skill records.

⚠️ **Important:** The schema contains `DROP TABLE IF EXISTS` statements. Run it only when setting up a fresh database because it can replace existing tables and data.

### 3. Configure and Run the Backend

Open PowerShell and navigate to the backend directory:

```powershell
cd backend
```

Configure your local MySQL credentials:

```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="YOUR_LOCAL_MYSQL_PASSWORD"
```

Replace the placeholder with your own MySQL password.

Start the Spring Boot application:

```powershell
.\mvnw spring-boot:run
```

Backend URL: http://localhost:8080

Keep the backend terminal running.

### 4. Install and Run the Frontend

Open another terminal and navigate to the frontend directory:

```powershell
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

Frontend URL: http://localhost:5173

Open the URL in your browser to access TEVORA.

## 🟧 Security

- Database credentials are configured through environment variables.
- Passwords are hashed using BCrypt before storage.
- Each developer uses their own local MySQL credentials.
- Never commit passwords, `.env` files containing secrets, or personal credentials to GitHub.

## 🟧 Team Development

Every team member can run TEVORA independently:

1. Clone the GitHub repository.
2. Create their local database using the provided schema.
3. Import the starter skills.
4. Configure their own MySQL credentials.
5. Start the Spring Boot backend.
6. Start the React frontend.

**Important:** Each developer has an independent local database. Data created on one laptop will not automatically appear on another laptop.

## 🟧 Project Status

![Status](https://img.shields.io/badge/Status-Completed-FF9933?style=for-the-badge&labelColor=000000)

TEVORA implements the core student collaboration workflow:

**Student Login → Project Creation → Project Applications → Application Acceptance → Team Formation**

## 🟧 Author

<div align="center">

**Thiyagarasan C**

B.E. Computer Science and Engineering  
Loyola-ICAM College of Engineering and Technology (LICET)

[![GitHub](https://img.shields.io/badge/GitHub-thiyagarasan6380--alt-FF9933?style=for-the-badge&labelColor=000000&logo=github&logoColor=FF9933)](https://github.com/thiyagarasan6380-alt)

</div>
