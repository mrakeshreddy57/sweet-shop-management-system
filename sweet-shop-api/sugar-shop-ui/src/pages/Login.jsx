import React, { useState } from "react";
import AuthService from "../services/auth.service";
import { useNavigate, Link } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      await AuthService.login(username, password);
      navigate("/dashboard");
      window.location.reload();
    } catch (error) {
      alert("Login Failed!");
    }
  };

  return (
    <div className="flex items-center justify-center h-screen">
      <div className="glass-card p-8 w-96 text-center">
        <h2 className="text-3xl font-bold text-sugar-dark mb-6">Welcome Back</h2>
        <form onSubmit={handleLogin}>
          <input className="input-field" type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
          <input className="input-field" type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
          <button type="submit" className="btn-primary w-full mt-4">Login</button>
        </form>
        <p className="mt-4 text-sm text-gray-600">New? <Link to="/register" className="font-bold underline">Create Account</Link></p>
      </div>
    </div>
  );
};

export default Login;