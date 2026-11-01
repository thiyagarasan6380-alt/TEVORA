import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API_BASE_URL from "../api";


function Login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();
    function login() {

    fetch(`${API_BASE_URL}/users/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username,
            password
        })
    })
    .then(response => response.json())
    .then(data => {

        if(data){

            localStorage.setItem("user", data.username);
            localStorage.setItem("userId", data.id);

            alert("Login Successful");

            navigate("/createproject");
            

        } else {

            alert("Invalid Username or Password");

        }

    });
    

}
    
  return (
    <div className="min-h-screen bg-orange-50 flex justify-center items-center px-4 py-10">

      <div className="bg-white p-8 md:p-10 rounded-2xl shadow-lg w-full max-w-md flex flex-col gap-5">

        <h1 className="text-3xl md:text-4xl font-bold text-orange-600 text-center mb-2">
          Login
        </h1>

        <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="w-full border border-gray-300 p-3 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500 transition"
        />

        <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="w-full border border-gray-300 p-3 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500 transition"
        />

        <button
            onClick={login}
            className="w-full bg-orange-500 text-white py-3 rounded-lg font-semibold shadow-sm hover:bg-orange-600 transition mt-2"
        >
            Login
        </button>

      </div>

    </div>
  );
}

export default Login;
