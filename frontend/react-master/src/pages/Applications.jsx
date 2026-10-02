import { useEffect, useState } from "react";

function Applications() {

    const [applications, setApplications] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/applications")
            .then(res => res.json())
            .then(data => setApplications(data));
    }, []);

    function acceptApplication(id){

        fetch(`http://localhost:8080/applications/accept/${id}`,{
            method:"PUT"
        })
        .then(res => res.json())
        .then(updated => {

            setApplications(prev =>
                prev.map(app =>
                    app.id === id ? updated : app
                )
            );

            alert("Applicant Accepted");
        });

    }

    return (
        <div className="p-8">
            <h1 className="text-3xl font-bold mb-6">
                Applications
            </h1>

            {applications.map(app => (

                <div
                    key={app.id}
                    className="border p-4 rounded-lg mb-4"
                >

                    <p>Application ID: {app.id}</p>
                    <p>Student ID: {app.studentId}</p>
                    <p>Project ID: {app.projectId}</p>
                    <p>Status: {app.status}</p>

                    {app.status === "Pending" && (
                        <button
                            onClick={() => acceptApplication(app.id)}
                            className="bg-green-500 text-white px-4 py-2 rounded mt-2"
                        >
                            Accept
                        </button>
                    )}

                </div>

            ))}
        </div>
    );
}

export default Applications;