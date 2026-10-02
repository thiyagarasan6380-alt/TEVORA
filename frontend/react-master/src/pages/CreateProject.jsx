import {useState,useEffect} from "react";
import ProjectCard from "../components/ProjectCard";

function CreateProject(){
    const [projectName,setProjectName]=useState("");
    const [skillName,setSkillName]=useState("");

    const [description,setDescription]=useState("");
   

    const [projects, setProjects] = useState([]);
    const [applications,setApplications]= useState([]);

        useEffect(() => {
            fetch("http://localhost:8080/projects")
                .then((response) => response.json())
                .then((data) => setProjects(data))
                .catch((error) => console.log(error));

                fetch("http://localhost:8080/applications")
                .then((response) => response.json())
                .then((data) => setApplications(data))
                .catch((error) => console.log(error));
        }, []);

    function Submit(){
        const newProject = {
            
            projectName: projectName,
            skillName: skillName,
            description:description,
            status: "Open",
            
            owner: localStorage.getItem("user"),
            
        };
        fetch("http://localhost:8080/projects", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newProject)
        })
        .then((response) => response.json())
        .then((data) => {
            setProjects((prev) => [...prev, data]);
        });
        setProjectName("");
        setSkillName("");
        setDescription("");
    }

    function Delete(indexToDelete){

    const projectId = projects[indexToDelete].id;

    fetch(`http://localhost:8080/projects/${projectId}`,{
        method:"DELETE"
    })
    .then(() => {
        const updateProjects = projects.filter(
            (_,index)=>index!==indexToDelete
        );

        setProjects(updateProjects);
    })
    .catch((error) => console.log(error));
}

    function Update(index){

    const newName = prompt(
        "Enter new project name",
        projects[index].projectName
    );

    const newSkill = prompt(
        "Enter skills",
        projects[index].skillName
    );

    if(!newName) return;

    const updatedProject = {
        ...projects[index],
        projectName: newName,
        skillName: newSkill
    };

    fetch(`http://localhost:8080/projects/${projects[index].id}`,{
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        },
        body: JSON.stringify(updatedProject)
    })
    .then(response => response.json())
    .then(data => {

        const updatedProjects = [...projects];
        updatedProjects[index] = data;

        setProjects(updatedProjects);
    });
}

    function changeStatus(index) {
    const project = projects[index];

    const newStatus =
        project.status === "Open" ? "Closed" : "Open";

    const updatedProject = {
        ...project,
        status: newStatus
    };

    fetch(`http://localhost:8080/projects/${project.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(updatedProject)
    })
    .then(response => response.json())
    .then(data => {
        const updatedProjects = [...projects];
        updatedProjects[index] = data;
        setProjects(updatedProjects);
    })
    .catch(error => console.log(error));
}

    const [searchTerm, setSearchTerm] = useState(""); 
    const filteredProjects = projects.filter((project) =>
    project.projectName
        .toLowerCase()
        .includes(searchTerm.toLowerCase())
    );
    function acceptApplicant(applicationId) {
    fetch(`http://localhost:8080/applications/accept/${applicationId}`, {
        method: "PUT"
    })
    .then(response => response.json())
    .then(data => {
        setApplications(prev =>
            prev.map(app =>
                app.id === data.id ? data : app
            )
        );

        alert("Applicant Accepted");
    })
    .catch(error => console.log(error));
}

function applyProject(projectId) {
    const studentId = Number(localStorage.getItem("userId"));

    fetch("http://localhost:8080/applications", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            studentId: studentId,
            projectId: projectId
        })
    })
    .then(res => res.json())
    .then(data => {
        alert("Application Submitted");
        console.log(data);
    })
    .catch(error => console.log(error));
}
    
    return(
        
        <div className="min-h-screen bg-orange-50 p-8">
            <div className="text-center mb-8">
                <h1 className="text-4xl font-bold text-orange-600">
                    Student Project Hub</h1>
                <p className="text-gray-600 mt-2">
                    Find. Collaborate. Build.</p>
            </div>
            <div className="bg-white shadow-lg rounded-xl p-6 max-w-md mx-auto flex flex-col gap-4">
            
            <input type="text" placeholder="Enter project name..." value={projectName} onChange={(e)=>setProjectName(e.target.value)} className="w-full border border-gray-300 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-orange-500 mb-4"/>

            <textarea placeholder="Enter project description..." value={description} onChange={(e) => setDescription(e.target.value)} className="w-full border border-gray-300 rounded-lg p-3"/>
          
            <input type="text" placeholder="Enter skill name..." value={skillName} onChange={(e)=>setSkillName(e.target.value)} className="w-full border border-gray-300 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-orange-500 mb-4"/> 
            
            <button className="bg-orange-500 text-white px-4 py-2 rounded hover:bg-orange-600 rounded-lg p-3" onClick={Submit} >Create Project</button>
            </div>
            <div className="grid ">
            <input
                type="text"
                placeholder="Search Project..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="bg-white border p-2 rounded-lg w-full mt-4 w-full border border-gray-300 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-orange-500 mb-4"/>
            
            </div>

            {filteredProjects.map((project,index) => (
                <ProjectCard
                        key={index}
                        project={project}
                        index={index}
                        Delete={Delete}
                        Update={Update}
                        changeStatus={changeStatus}
                        applyProject={applyProject}
                        acceptApplicant={acceptApplicant}
                        applications={applications}
                />     
            ))
            }
            
        </div>
    );
}

export default CreateProject;