import { useState } from "react";
import { useNavigate } from "react-router-dom";



function Login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();
    function login() {

    fetch("http://localhost:8080/users/login", {
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
            window.location.reload();

        } else {

            alert("Invalid Username or Password");

        }

    });
    

}
    
  return (
    <div className="min-h-screen bg-orange-50 flex justify-center items-center">

      <div className="bg-white p-8 rounded-xl shadow-lg w-96">

        <h1 className="text-3xl font-bold text-orange-600 text-center">
          Login
        </h1>

        <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="w-full border p-3 rounded-lg mt-6"
        />

        <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="w-full border p-3 rounded-lg mt-4"
        />

        <button
            onClick={login}
            className="w-full bg-orange-500 text-white py-3 rounded-lg mt-6"
        >
            Login
        </button>

      </div>

    </div>
  );
}

export default Login;