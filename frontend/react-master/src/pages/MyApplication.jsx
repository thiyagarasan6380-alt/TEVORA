import { useEffect, useState } from "react";

function MyApplications() {

    const [applications, setApplications] = useState([]);

    useEffect(() => {

        const studentId = 1; // temporary

        fetch(`http://localhost:8080/applications/student/${studentId}`)
            .then(response => response.json())
            .then(data => setApplications(data))
            .catch(error => console.log(error));

    }, []);

    return (
        <div className="min-h-screen bg-orange-50 p-8">

            <h1 className="text-4xl font-bold text-orange-600 text-center">
                My Applications
            </h1>

            <div className="mt-8">

                {applications.length === 0 ? (

                    <p className="text-center text-gray-500">
                        No Applications Found
                    </p>

                ) : (

                    applications.map((app) => (

                        <div
                            key={app.id}
                            className="bg-white p-4 rounded-xl shadow-md mb-4"
                        >

                            <h2 className="font-bold">
                                Project ID: {app.projectId}
                            </h2>

                            <p>
                                Status: {app.status}
                            </p>

                        </div>

                    ))

                )}

            </div>

        </div>
    );
}

export default MyApplications;