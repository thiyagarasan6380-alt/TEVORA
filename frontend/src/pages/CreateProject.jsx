
import { useState, useEffect } from "react";
import ProjectCard from "../components/ProjectCard";

function CreateProject() {
    const [projectName, setProjectName] = useState("");
    const [skillName, setSkillName] = useState("");
    const [description, setDescription] = useState("");

    const [projects, setProjects] = useState([]);
    const [applications, setApplications] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");

    const userId = Number(localStorage.getItem("userId"));

    useEffect(() => {
        fetch("http://localhost:8080/projects")
            .then(response => {
                if (!response.ok) throw new Error("Failed to load projects");
                return response.json();
            })
            .then(data => setProjects(Array.isArray(data) ? data : []))
            .catch(error => console.error(error));

        fetch("http://localhost:8080/applications")
            .then(response => {
                if (!response.ok) throw new Error("Failed to load applications");
                return response.json();
            })
            .then(data => setApplications(Array.isArray(data) ? data : []))
            .catch(error => console.error(error));
    }, []);

    async function Submit() {
        if (!projectName.trim() || !description.trim()) {
            alert("Please enter project name and description");
            return;
        }

        if (!userId) {
            alert("Please login before creating a project");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/projects", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    projectName: projectName.trim(),
                    description: description.trim(),
                    ownerId: userId
                })
            });

            if (!response.ok) {
                const message = await response.text();
                throw new Error(message || "Project creation failed");
            }

            const data = await response.json();

            setProjects(prev => [...prev, data]);

            setProjectName("");
            setSkillName("");
            setDescription("");

            alert("Project created successfully!");
        } catch (error) {
            console.error("Create project error:", error);
            alert(error.message);
        }
    }

    async function Delete(indexToDelete) {
        const project = projects[indexToDelete];

        if (!project) return;

        try {
            const response = await fetch(
                `http://localhost:8080/projects/${project.id}?ownerId=${userId}`,
                { method: "DELETE" }
            );

            if (!response.ok) {
                throw new Error("Unable to delete project");
            }

            setProjects(prev =>
                prev.filter(item => item.id !== project.id)
            );
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    async function Update(index) {
        const project = projects[index];

        const newName = prompt(
            "Enter new project name",
            project.projectName
        );

        if (!newName || !newName.trim()) return;

        const updatedProject = {
            ...project,
            projectName: newName.trim()
        };

        try {
            const response = await fetch(
                `http://localhost:8080/projects/${project.id}?ownerId=${userId}`,
                {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(updatedProject)
                }
            );

            if (!response.ok) {
                throw new Error("Unable to update project");
            }

            const data = await response.json();

            setProjects(prev =>
                prev.map(item =>
                    item.id === project.id ? data : item
                )
            );
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    async function changeStatus(index) {
        const project = projects[index];

        const newStatus =
            project.status?.toUpperCase() === "OPEN"
                ? "CLOSED"
                : "OPEN";

        try {
            const response = await fetch(
                `http://localhost:8080/projects/${project.id}/status?ownerId=${userId}&status=${newStatus}`,
                { method: "PUT" }
            );

            if (!response.ok) {
                throw new Error("Unable to change project status");
            }

            const data = await response.json();

            setProjects(prev =>
                prev.map(item =>
                    item.id === project.id ? data : item
                )
            );
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    const filteredProjects = projects.filter(project =>
        String(project.projectName ?? "")
            .toLowerCase()
            .includes(searchTerm.toLowerCase())
    );

    async function acceptApplicant(applicationId) {
        try {
            const response = await fetch(
                `http://localhost:8080/applications/accept/${applicationId}?ownerId=${userId}`,
                { method: "PUT" }
            );

            if (!response.ok) {
                throw new Error("Unable to accept application");
            }

            const data = await response.json();

            setApplications(prev =>
                prev.map(app =>
                    app.id === data.id ? data : app
                )
            );

            alert("Applicant accepted!");
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    async function applyProject(projectId) {
        const studentId = userId;

        if (!studentId) {
            alert("Please login first");
            return;
        }

        try {
            const response = await fetch(
                `http://localhost:8080/applications?studentId=${studentId}&projectId=${projectId}`,
                { method: "POST" }
            );

            if (!response.ok) {
                const message = await response.text();
                throw new Error(message || "Application failed");
            }

            const data = await response.json();

            setApplications(prev => [...prev, data]);

            alert("Application submitted successfully!");
        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    }

    return (
        <div className="min-h-screen bg-orange-50 px-4 py-10">
            <div className="max-w-5xl mx-auto">

                <div className="text-center mb-10">
                    <h1 className="text-4xl md:text-5xl font-bold text-orange-600">
                        Student Project Hub
                    </h1>

                    <p className="text-gray-600 mt-3 text-lg">
                        Find. Collaborate. Build.
                    </p>
                </div>

                <div className="bg-white shadow-lg rounded-2xl p-6 md:p-8 max-w-lg mx-auto flex flex-col gap-5">

                    <input
                        type="text"
                        placeholder="Enter project name..."
                        value={projectName}
                        onChange={e => setProjectName(e.target.value)}
                        className="w-full border border-gray-300 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-orange-500 transition"
                    />

                    <textarea
                        placeholder="Enter project description..."
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                        rows={4}
                        className="w-full border border-gray-300 rounded-lg p-3 resize-none focus:outline-none focus:ring-2 focus:ring-orange-500 transition"
                    />

                    <input
                        type="text"
                        placeholder="Enter skill name..."
                        value={skillName}
                        onChange={e => setSkillName(e.target.value)}
                        className="w-full border border-gray-300 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-orange-500 transition"
                    />

                    <button
                        className="bg-orange-500 text-white px-4 py-3 font-semibold shadow-sm hover:bg-orange-600 rounded-lg transition"
                        onClick={Submit}
                    >
                        Create Project
                    </button>
                </div>

                <div className="max-w-lg mx-auto mt-10">
                    <input
                        type="text"
                        placeholder="Search Project..."
                        value={searchTerm}
                        onChange={e => setSearchTerm(e.target.value)}
                        className="bg-white w-full border border-gray-300 rounded-lg p-3 shadow-sm focus:outline-none focus:ring-2 focus:ring-orange-500 transition"
                    />
                </div>

                <div className="mt-8 flex flex-col gap-6">
                    {filteredProjects.map((project, index) => (
                        <ProjectCard
                            key={project.id}
                            project={project}
                            index={index}
                            Delete={Delete}
                            Update={Update}
                            changeStatus={changeStatus}
                            applyProject={applyProject}
                            acceptApplicant={acceptApplicant}
                            applications={applications}
                        />
                    ))}
                </div>

            </div>
        </div>
    );
}

export default CreateProject;
