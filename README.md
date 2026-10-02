<div align="center">



![TEVORA](https://img.shields.io/badge/TEVORA-Student_Project_Collaboration_Hub-000000?style=for-the-badge&labelColor=FF9933)





![Status](https://img.shields.io/badge/Status-Completed-FF9933?style=flat-square&labelColor=000000)




![React](https://img.shields.io/badge/Frontend-React-FF9933?style=flat-square&labelColor=000000&logo=react&logoColor=FF9933)




![Spring Boot](https://img.shields.io/badge/Backend-Spring_Boot-FF9933?style=flat-square&labelColor=000000&logo=springboot&logoColor=FF9933)




![MySQL](https://img.shields.io/badge/Database-MySQL-FF9933?style=flat-square&labelColor=000000&logo=mysql&logoColor=FF9933)



</div>

---

# 🟧 TEVORA

> **Find teammates for your academic projects, based on the skills you need.**

TEVORA is a web platform where students can **create projects**, **discover projects**, **apply to join teams**, and **manage team members**, all in one place.

---

## 🟧 Features

| For Students | For Project Owners |
|---|---|
| 🔐 Login | ➕ Create projects |
| 🔍 Search & browse projects | ✏️ Edit projects |
| 📝 Apply to join projects | 🗑️ Delete projects |
| 📄 View your applications | 🔓 Open / Close project status |
| 👥 View accepted team members | ✅ Accept student applications |

💾 All project and application data is stored in **MySQL**.

---

## 🟧 How It Works

```text
 Login
   ↓
 Browse Projects
   ↓
 Create a Project  or  Apply to Join
   ↓
 Owner Reviews Application
   ↓
 Application Accepted
   ↓
 Team Member Added  →  Collaboration Starts 🚀
```

---

## 🟧 Technology Stack

| Layer | Technologies |
|---|---|
| **Frontend** | React, Tailwind CSS, JavaScript |
| **Backend** | Java, Spring Boot, Spring Data JPA |
| **Database** | MySQL |
| **Tools** | Visual Studio Code, Git, GitHub, Postman |

---

## 🟧 Project Structure

```text
TEVORA/
│
├── frontend/
│   └── react-master/      → React app
│
├── backend/
│   └── studenthub/        → Spring Boot app
│
├── .gitignore
└── README.md
```

---

## 🟧 Main Backend APIs

### 👤 Users

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/users/register` | Register a new user |
| `POST` | `/users/login` | Log in |
| `GET` | `/users/{username}` | Get user by username |
| `GET` | `/users/id/{id}` | Get user by ID |

### 📁 Projects

| Method | Endpoint | Purpose |
|---|---|---|
| `GET` | `/projects` | List all projects |
| `POST` | `/projects` | Create a project |
| `PUT` | `/projects/{id}` | Update a project |
| `DELETE` | `/projects/{id}` | Delete a project |

### 📨 Applications

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/applications` | Apply to a project |
| `GET` | `/applications` | List all applications |
| `GET` | `/applications/project/{projectId}` | Applications for a project |
| `GET` | `/applications/student/{studentId}` | Applications by a student |
| `GET` | `/applications/accepted` | Accepted applications |
| `PUT` | `/applications/accept/{id}` | Accept an application |

---

## 🟧 Database

MySQL stores:

- **Users**
- **Projects**
- **Applications**
- **Project-related information**

---

## 🟧 Running the Project

### 1️⃣ Start MySQL
Make sure MySQL is running and the required database exists.

### 2️⃣ Start the Backend
Open the Spring Boot project in `backend/studenthub` and run it.

🔗 Runs on: **http://localhost:8080**

### 3️⃣ Start the Frontend

```bash
cd frontend/react-master
npm install
npm run dev
```

🔗 Runs on: **http://localhost:5173**

---

## 🟧 Security

- Database configuration with local credentials is excluded from GitHub using `.gitignore`.
- ⚠️ **Never commit passwords or other sensitive credentials.**

---

## 🟧 Project Status



![Status](https://img.shields.io/badge/Status-Completed-FF9933?style=for-the-badge&labelColor=000000)



TEVORA supports the complete basic workflow: **student login → project creation → applications → team formation.**

---

## 🟧 Author

<div align="center">

**Thiyagarasan C**
B.E. Computer Science and Engineering
Loyola-ICAM College of Engineering and Technology (LICET)

[

![GitHub](https://img.shields.io/badge/GitHub-thiyagarasan6380--alt-FF9933?style=for-the-badge&labelColor=000000&logo=github&logoColor=FF9933)

](https://github.com/thiyagarasan6380-alt)

</div>
