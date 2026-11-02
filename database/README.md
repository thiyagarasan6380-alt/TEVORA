# ðŸŸ§ TEVORA

### Student Project Collaboration Hub

**Find teammates for your academic projects based on the skills you need.**

TEVORA is a web application where students can create projects, discover opportunities, apply to join teams, and manage project collaboration.

## âœ¨ Features

### For Students

- Log in to their accounts
- Browse available projects
- Create, edit, and delete projects
- Apply to join projects
- Track application status
- View accepted team members

### For Project Owners

- Manage their projects
- Open or close applications
- View applications received
- Accept student applications
- Build project teams

> **Note:** Student registration is currently supported through the backend API. A registration page is not included in the frontend.

## ðŸ› ï¸ Technology Stack

| Layer       | Technologies                    |
| ----------- | ------------------------------- |
| Frontend    | React, JavaScript, Tailwind CSS |
| Backend     | Java 21, Spring Boot            |
| Database    | MySQL 8, Spring Data JPA        |
| Build Tools | Maven, npm                      |
| Development | VS Code, Git, GitHub            |

## ðŸ“ Project Structure

```text
TEVORA/
â”‚
â”œâ”€â”€ backend/
â”‚   â”œâ”€â”€ src/
â”‚   â”œâ”€â”€ pom.xml
â”‚   â”œâ”€â”€ mvnw
â”‚   â””â”€â”€ mvnw.cmd
â”‚
â”œâ”€â”€ frontend/
â”‚   â”œâ”€â”€ src/
â”‚   â”œâ”€â”€ public/
â”‚   â”œâ”€â”€ package.json
â”‚   â””â”€â”€ package-lock.json
â”‚
â”œâ”€â”€ database/
â”‚   â”œâ”€â”€ tevora_Schema.sql
â”‚   â”œâ”€â”€ tevora_sample_data.sql
â”‚   â””â”€â”€ README.md
â”‚
â”œâ”€â”€ .gitignore
â””â”€â”€ README.md
```

## âš™ï¸ Requirements

Install the following before running TEVORA:

- Java JDK 21
- MySQL Server 8.0 or later
- MySQL Workbench
- Node.js and npm
- Git

## ðŸš€ Installation and Setup

Follow these steps to run TEVORA on your local computer.

### Step 1: Clone the Repository

Open a terminal and execute:

```bash
git clone https://github.com/thiyagarasan6380-alt/TEVORA.git
cd TEVORA
```

### Step 2: Set Up the Database

1. Open MySQL Workbench.
2. Connect to your local MySQL server.
3. Open the file `database/tevora_Schema.sql` from the cloned project.
4. Execute the complete SQL script.
5. Open `database/tevora_sample_data.sql`.
6. Execute it to insert the starter skills.

This creates the local `tevora_db` database, its nine tables, and the starter skill records.

**âš ï¸ Important:** The schema script contains `DROP TABLE IF EXISTS` statements. Run it only when setting up a fresh database. It can delete existing tables and their data.

### Step 3: Configure and Run the Backend

1. Open a new PowerShell terminal.
2. Navigate to the backend directory:

```powershell
cd backend
```

3. Set your own local MySQL credentials:

```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="YOUR_LOCAL_MYSQL_PASSWORD"
```

Replace the placeholder with your own MySQL password. Do not share or commit your password.

4. Start the Spring Boot application:

```powershell
.\mvnw spring-boot:run
```

Wait until Spring Boot starts successfully.

**Backend URL:** http://localhost:8080

Keep this terminal running while using TEVORA.

### Step 4: Install and Run the Frontend

1. Open another terminal.
2. Navigate to the frontend directory:

```powershell
cd frontend
```

3. Install the project dependencies:

```powershell
npm install
```

4. Start the Vite development server:

```powershell
npm run dev
```

**Frontend URL:** http://localhost:5173

Open this URL in your browser to use TEVORA.

## ðŸ”„ How TEVORA Works

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
                       Create / Update Team
                               |
                         Collaboration
```

## ðŸ—„ï¸ Database Design

TEVORA uses nine MySQL tables:

1. `users`
2. `students`
3. `projects`
4. `applications`
5. `skills`
6. `project_skills`
7. `student_skills`
8. `teams`
9. `team_members`

Primary keys, foreign keys, and unique constraints help maintain relationships and data integrity.

## ðŸ” Security and Configuration

- Each developer uses their own local MySQL credentials.
- Database passwords are supplied through environment variables.
- Passwords are hashed using BCrypt before storage.
- Never commit passwords or personal credentials to GitHub.

## ðŸ‘¥ Team Development

Every team member should:

1. Clone the GitHub repository.
2. Set up their own local MySQL database by executing the schema.
3. Import the starter skills.
4. Configure their own MySQL credentials.
5. Run the backend.
6. Run the frontend.

**Important:** Each developer has an independent local database. Data created on one laptop will not automatically appear on another laptop.

## ðŸ‘¨â€ðŸ’» Author

**Thiyagarasan C**

B.E. Computer Science and Engineering
Loyola-ICAM College of Engineering and Technology (LICET)

GitHub: https://github.com/thiyagarasan6380-alt
