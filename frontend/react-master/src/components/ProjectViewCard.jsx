import { Link } from "react-router-dom";

function ProjectViewCard({ project }) {
    return (
        <Link to={`/project/${project.id}`}>
        <div className="bg-white rounded-xl shadow-md p-5 mt-4">

            <div className="flex justify-between items-center">

                <h2 className="text-xl font-bold text-orange-600">
                    🚀 {project.projectName}
                </h2>

                <span
                    className={
                        project.status === "Open"
                        ? "bg-green-100 text-green-700 px-3 py-1 rounded-full text-sm"
                        : "bg-red-100 text-red-700 px-3 py-1 rounded-full text-sm"
                    }
                >
                    {project.status}
                </span>

            </div>

            <p className="text-gray-600 mt-3">
                {project.description}
            </p>

            <div className="flex flex-wrap gap-2 mt-4">

                {project.skillName?.map((skill, i) => (
                    <span
                        key={i}
                        className="bg-orange-100 text-orange-700 px-3 py-1 rounded-full"
                    >
                        {skill}
                    </span>
                ))}

            </div>

            <p className="mt-4 text-gray-700">
                👥 {project.teamMembers?.length || 0} Team Members
            </p>

        </div>
        </Link>
    );
}

export default ProjectViewCard;