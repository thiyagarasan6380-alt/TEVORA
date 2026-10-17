import { Link } from "react-router-dom";
import {useEffect,useState} from "react";
function Home() {

  const [projects,setProjects]=useState([]);
  const [applications,setApplications]=useState([]);
  useEffect(() => {

    fetch("http://localhost:8080/projects")
        .then(response => response.json())
        .then(data => setProjects(data))
        .catch(error => console.log(error));

    fetch("http://localhost:8080/applications")
        .then(response => response.json())
        .then(data => setApplications(data))
        .catch(error => console.log(error));

}, []);
    const totalProject=projects.length;
    const totalApplicant = applications.length;

    const totalTeamMembers = applications.filter(
      (application) => application.status === "Accepted"
    ).length;
    const openProjects=projects.filter(
      (project)=>project.status==="Open"
    ).length;
    const currentUser=localStorage.getItem("user");
    const myProjects=projects.filter(
      (project)=>project.owner===currentUser
    );

  return (
    <div className="flex justify-center min-h-screen bg-orange-50 px-4 py-10">

      <div className="text-center w-full max-w-5xl">

        <h1 className="text-5xl md:text-6xl font-bold text-orange-600">
          TEVORA
        </h1>
        <p className="text-lg text-orange-700 mt-3">
          Welcome, {currentUser || "Guest"} 
       </p>

        <p className="text-xl md:text-2xl font-medium text-gray-700 mt-6">
          Find. Collaborate. Build.
        </p>

        <p className="text-gray-600 mt-4 max-w-2xl mx-auto leading-relaxed">
          Connect with students, discover exciting projects,
          build strong teams, and turn ideas into reality.
        </p>


        <div className="flex flex-wrap justify-center gap-4 mt-8">

          <Link
            to="/projects"
            className="bg-orange-500 text-white px-6 py-3 rounded-lg shadow-sm hover:bg-orange-600 transition"
          >
            Browse Projects
          </Link>

          <Link
            to="/CreateProject"
            className="border border-orange-500 text-orange-500 px-6 py-3 rounded-lg hover:bg-orange-100 transition"
          >
            Create Project
          </Link>

        </div>

        <div className="grid grid-cols-2 md:grid-cols-4 gap-4 md:gap-6 mt-16">

        <div className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition">
          <h2 className="text-3xl font-bold text-orange-600">
            {totalApplicant}+
          </h2>
          <p className="text-gray-600 mt-2">
            Applicant
          </p>
        </div>

        <div className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition">
          <h2 className="text-3xl font-bold text-orange-600">
            {totalProject}
          </h2>
          <p className="text-gray-600 mt-2">
            Projects
          </p>
        </div>

        <div className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition">
          <h2 className="text-3xl font-bold text-orange-600">
            {totalTeamMembers}
          </h2>
          <p className="text-gray-600 mt-2">
            Teams
          </p>
        </div>
        <div className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition">
          <h2 className="text-3xl font-bold text-orange-600">
            {openProjects}
          </h2>
          <p className="text-gray-600 mt-2">
            Open Project
          </p>
        </div>

      </div>
      <div className="mt-20">

    <h2 className="text-2xl md:text-3xl font-bold text-center text-orange-600">
      Why Use Student Project Hub?
    </h2>

    <div className="grid md:grid-cols-3 gap-6 mt-8">

      <div className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition">
        <h3 className="text-xl font-semibold">
          🔍 Find Projects
        </h3>

        <p className="text-gray-600 mt-2 leading-relaxed">
          Discover projects that match your skills and interests.
        </p>
      </div>

      <div className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition">
        <h3 className="text-xl font-semibold">
          🤝 Build Teams
        </h3>

        <p className="text-gray-600 mt-2 leading-relaxed">
          Connect with students and form strong project teams.
        </p>
      </div>

      <div className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition">
        <h3 className="text-xl font-semibold">
          🏆 Gain Experience
        </h3>

        <p className="text-gray-600 mt-2 leading-relaxed">
          Work on real projects and improve your portfolio.
        </p>
      </div>
      

  </div>
  <div className="mt-20">

    <h2 className="text-2xl md:text-3xl font-bold text-orange-600 text-center">
        My Projects
    </h2>

    <div className="gap-4 mt-8 max-w-3xl mx-auto text-left" >

        {myProjects.length === 0 ? (

            <p className="text-center text-gray-500 bg-white p-6 rounded-xl shadow-sm">
                No Projects Created Yet
            </p>

        ) : (

            myProjects.map((project) => (
              <div
                  key={project.id}
                  className="bg-white p-5 rounded-xl shadow-md hover:shadow-lg transition mb-4"
              >
                  <h3 className="font-bold text-lg text-orange-600">
                      🚀 {project.projectName}
                  </h3>

                  <p className="text-gray-600 mt-2">
                      Status: {project.status}
                  </p>

                  <p className="text-sm text-gray-500 mt-1">
                      Created: {project.createdAt}
                  </p>

              </div>
          ))
        )}

      </div>

      </div>
        <div className="text-center mt-20 bg-white rounded-2xl shadow-md px-6 py-12">

        <h2 className="text-2xl md:text-3xl font-bold text-orange-600">
          Ready to Build Something Amazing?
        </h2>

        <p className="text-gray-600 mt-3 max-w-xl mx-auto">
          Join projects, find teammates, and bring ideas to life.
        </p>

        <Link
          to="/CreateProject"
          className="inline-block mt-6 bg-orange-500 text-white px-8 py-3 rounded-lg shadow-sm hover:bg-orange-600 transition"
        >
          Get Started
        </Link>

      </div>

</div>

      </div>
      

    </div>
  );
}

export default Home;

