import { useState, useEffect } from "react";
import ProjectViewCard from "../components/ProjectViewCard";
function Projects() {

    const [projects, setProjects] = useState([]);

    useEffect(() => {
        const savedProjects =
            JSON.parse(localStorage.getItem("projects")) || [];

        setProjects(savedProjects);
    }, []);

    return (
        <div className="min-h-screen bg-orange-50 p-8">

            <h1 className="text-4xl font-bold text-orange-600 text-center">
                All Projects
            </h1>

            <div className="mt-8">
                {projects.map((project,index) => (
                    <ProjectViewCard
                        key={index}
                        project={project}
                        
                    />
                ))}
            </div>

        </div>
    );
}

export default Projects;