import { useEffect, useState } from "react";

function TeamMembers() {

    const [members, setMembers] = useState([]);

    useEffect(() => {

        fetch("http://localhost:8080/applications/accepted")
            .then(res => res.json())
            .then(data => setMembers(data));

    }, []);

    return (
        <div className="p-8">

            <h1 className="text-3xl font-bold mb-6">
                Team Members
            </h1>

            {members.map(member => (

                <div
                    key={member.id}
                    className="border p-4 rounded-lg mb-4"
                >
                    <p>Student ID: {member.studentId}</p>
                    <p>Project ID: {member.projectId}</p>
                    <p>Status: {member.status}</p>
                </div>

            ))}

        </div>
    );
}

export default TeamMembers;