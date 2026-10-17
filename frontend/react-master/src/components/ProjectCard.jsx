import {useState,useEffect}from "react";
import API_BASE_URL from "../api";

function ProjectCard({
    project,
    index,
    Delete,
    Update,
    changeStatus,
    applyProject,
    acceptApplicant,
    applications
}) {

    const currentUser = localStorage.getItem("user");
    const isOwner = currentUser === project.owner;
    const [userNames,setUserNames]=useState({});

    useEffect(() => {
    const studentIds = [
        ...new Set(
            applications.map((app) => app.studentId)
        )
    ];

    Promise.all(
        studentIds.map((id) =>
            fetch(`${API_BASE_URL}/users/id/${id}`)
                .then((res) => res.json())
                .then((user) => ({
                    id,
                    username: user.username
                }))
        )
    ).then((users) => {
        const names = {};

        users.forEach((user) => {
            names[user.id] = user.username;
        });

        setUserNames(names);
    });
}, [applications]);

    const skills =
    typeof project.skillName === "string"
        ? project.skillName.split(",")
        : project.skillName || [];
const projectApplications = applications.filter(
    (app) => app.projectId === project.id
);

const pendingApplications = projectApplications.filter(
    (app) => app.status === "Pending"
);

const acceptedApplications = projectApplications.filter(
    (app) => app.status === "Accepted"
);

    return (
        <div className="bg-white rounded-2xl shadow-md hover:shadow-lg transition p-6 md:p-8 max-w-3xl w-full mx-auto">

            <div className="flex flex-col sm:flex-row justify-between items-start gap-4 mb-6">

                <div className="flex-1">
                    <h2 className="text-2xl font-bold text-orange-600">
                        🚀 {project.projectName}
                    </h2>

                    <p className="text-sm text-gray-500 mt-2">
                        Created by: 👤 {project.owner}
                    </p>

                    <p className="text-sm text-gray-500 mt-1">
                        {project.createdAt}
                    </p>

                    <p className="text-gray-600 mt-3 max-w-lg leading-relaxed">
                        {project.description}
                    </p>
                </div>

                <span
                    className={
                        project.status === "Open"
                            ? "bg-green-100 text-green-700 px-3 py-1 rounded-full text-sm font-medium whitespace-nowrap"
                            : "bg-red-100 text-red-700 px-3 py-1 rounded-full text-sm font-medium whitespace-nowrap"
                    }
                >
                    {project.status}
                </span>

            </div>

            <div className="mt-6">

                <p className="text-gray-500 text-sm font-medium mb-3">
                    Required Skills
                </p>

                <div className="flex flex-wrap gap-2">

                    {skills.length === 0 ? (
                        <p className="text-gray-400 text-sm">No Skills Added</p>
                    ) : (
                        skills.map((skill, i) => (
                            <span
                                key={i}
                                className="bg-orange-100 text-orange-700 px-3 py-1 rounded-full text-sm"
                            >
                                {skill}
                            </span>
                        ))
                    )}

                </div>

            </div>

            <div className="mt-6 border-t border-gray-200 pt-5 flex flex-wrap gap-x-8 gap-y-2">

                <p className="text-gray-700 font-medium">
                    👥 {pendingApplications.length} Applicants
                </p>

                <p className="text-gray-700 font-medium">
                    👨‍💻 {acceptedApplications.length} Team Members
                </p>

            </div>

            <div className="mt-6">

                <h3 className="font-semibold text-gray-800 mb-3">
                    Applicants
                </h3>

                {pendingApplications.length === 0 ? (
                    <p className="text-gray-400 text-sm">
                        No applicants yet
                    </p>
                ) : (
                    pendingApplications.map((application) => (
                        <div
                            key={application.id}
                            className="flex justify-between items-center gap-3 bg-gray-50 rounded-lg p-3 mb-2"
                        >
                            <span className="text-gray-700">
                                👤 {userNames[application.studentId] || "Loading..."}
                            </span>

                            {isOwner && (
                                <button
                                    onClick={() => acceptApplicant(application.id)}
                                    className="bg-green-500 text-white px-4 py-1.5 rounded-lg text-sm font-medium hover:bg-green-600 transition"
                                >
                                    Accept
                                </button>
                            )}
                        </div>
                    ))
                )}

            </div>

            <div className="mt-6">

                <p className="font-semibold text-gray-800 mb-3">
                    Team Members
                </p>

                {acceptedApplications.length === 0 ? (
                    <p className="text-gray-400 text-sm">
                        No team members yet
                    </p>
                ) : (
                    acceptedApplications.map((application) => (
                        <p key={application.id} className="text-gray-700 mb-1">
                            👨‍💻 {userNames[application.studentId] || "Loading..."}
                        </p>
                    ))
                )}

            </div>

            <div className={!isOwner ? "mt-8" : ""}>

                {!isOwner && (
                    <button
                        onClick={() => applyProject(project.id)}
                        className="w-full bg-orange-500 text-white py-3 rounded-lg font-medium shadow-sm hover:bg-orange-600 transition"
                    >
                        Apply
                    </button>
                )}

            </div>

            <div className={isOwner ? "flex gap-3 mt-8" : ""}>

                {isOwner && (
                    <>
                        <button
                            onClick={() => Update(index)}
                            className="flex-1 border border-orange-500 text-orange-500 py-2.5 rounded-lg font-medium hover:bg-orange-100 transition"
                        >
                            Edit
                        </button>

                        <button
                            onClick={() => changeStatus(index)}
                            className="flex-1 border border-orange-500 text-orange-500 py-2.5 rounded-lg font-medium hover:bg-orange-100 transition"
                        >
                            Status
                        </button>

                        <button
                            onClick={() => Delete(index)}
                            className="flex-1 bg-red-500 text-white py-2.5 rounded-lg font-medium hover:bg-red-600 transition"
                        >
                            Delete
                        </button>
                    </>
                )}

            </div>

        </div>
    );
}

export default ProjectCard;
