import { useParams } from "react-router-dom";

function ProjectDetails() {
    const { id } = useParams();

    const projects =
        JSON.parse(localStorage.getItem("projects")) || [];

    const project = projects.find(
        (p) => p.id === Number(id)
    );

    if (!project) {
        return <h1>Project Not Found</h1>;
    }

    return (
        <div className="min-h-screen bg-orange-50 p-8">

            <div className="bg-white rounded-xl shadow-lg p-6">

                <h1 className="text-3xl font-bold text-orange-600">
                    🚀 {project.projectName}
                </h1>

                <p className="mt-4 text-gray-600">
                    {project.description}
                </p>

            </div>

        </div>
    );
}

export default ProjectDetails;