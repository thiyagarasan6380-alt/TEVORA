import {useState,useEffect}from "react";

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
            fetch(`http://localhost:8080/users/id/${id}`)
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
        <div className="bg-white rounded-xl shadow-md p-5 mt-4">

            <div className="flex justify-between items-start mb-4">

                <div>
                    <h2 className="text-2xl font-bold text-orange-600">
                        🚀 {project.projectName}
                    </h2>

                    <p className="text-sm text-gray-500 mt-1">
                        Created by: 👤 {project.owner}
                    </p>

                    <p className="text-sm text-gray-500">
                        {project.createdAt}
                    </p>

                    <p className="text-gray-600 mt-2 max-w-lg">
                        {project.description}
                    </p>
                </div>

                <span
                    className={
                        project.status === "Open"
                            ? "bg-green-100 text-green-700 px-3 py-1 rounded-full text-sm font-medium"
                            : "bg-red-100 text-red-700 px-3 py-1 rounded-full text-sm font-medium"
                    }
                >
                    {project.status}
                </span>

            </div>

            <div className="mt-5">

                <p className="text-gray-500 text-sm mb-3">
                    Required Skills
                </p>

                <div className="flex flex-wrap gap-2">

                    {skills.length === 0 ? (
                        <p>No Skills Added</p>
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

            <div className="mt-6 border-t pt-4">

                <p className="text-gray-700 font-medium">
                    👥 {pendingApplications.length} Applicants
                </p>

                <p className="text-gray-700 mt-1">
                    👨‍💻 {acceptedApplications.length} Team Members
                </p>

            </div>

            <div className="mt-4">

                <h3 className="font-semibold text-gray-800 mb-2">
                    Applicants
                </h3>

                {pendingApplications.length === 0 ? (
                    <p className="text-gray-400">
                        No applicants yet
                    </p>
                ) : (
                    pendingApplications.map((application) => (
                        <div
                            key={application.id}
                            className="flex justify-between items-center bg-gray-50 rounded-lg p-3 mb-2"
                        >
                            <span>
                                👤 {userNames[application.studentId] || "Loading..."}
                            </span>

                            {isOwner && (
                                <button
                                    onClick={() => acceptApplicant(application.id)}
                                    className="bg-green-500 text-white px-3 py-1 rounded-lg"
                                >
                                    Accept
                                </button>
                            )}
                        </div>
                    ))
                )}

            </div>

            <div className="mt-4">

                <p className="font-semibold">
                    Team Members
                </p>

                {acceptedApplications.length === 0 ? (
                    <p className="text-gray-400">
                        No team members yet
                    </p>
                ) : (
                    acceptedApplications.map((application) => (
                        <p key={application.id}>
                            👨‍💻 {userNames[application.studentId] || "Loading..."}
                        </p>
                    ))
                )}

            </div>

            <div className="mt-6">

                {!isOwner && (
                    <button
                        onClick={() => applyProject(project.id)}
                        className="w-full bg-orange-500 text-white py-3 rounded-lg font-medium"
                    >
                        Apply
                    </button>
                )}

            </div>

            <div className="flex gap-2 mt-4">

                {isOwner && (
                    <>
                        <button
                            onClick={() => Update(index)}
                            className="flex-1 border border-orange-500 text-orange-500 py-2 rounded-lg"
                        >
                            Edit
                        </button>

                        <button
                            onClick={() => changeStatus(index)}
                            className="flex-1 border border-orange-500 text-orange-500 py-2 rounded-lg"
                        >
                            Status
                        </button>

                        <button
                            onClick={() => Delete(index)}
                            className="flex-1 bg-red-500 text-white py-2 rounded-lg"
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