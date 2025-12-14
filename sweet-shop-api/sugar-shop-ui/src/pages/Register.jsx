import React, { useState } from "react";
import AuthService from "../services/auth.service";
import { useNavigate, Link } from "react-router-dom";

const Register = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      await AuthService.register(username, password);
      alert("Success! Please login.");
      navigate("/login");
    } catch (error) {
      alert("Username taken!");
    }
  };

  return (
    <div className="flex items-center justify-center h-screen">
      <div className="glass-card p-8 w-96 text-center">
        <h2 className="text-3xl font-bold text-sugar-dark mb-6">Join Us</h2>
        <form onSubmit={handleRegister}>
          <input className="input-field" type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
          <input className="input-field" type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
          <button type="submit" className="btn-primary w-full mt-4">Sign Up</button>
        </form>
        <p className="mt-4 text-sm text-gray-600">Have an account? <Link to="/login" className="font-bold underline">Login</Link></p>
      </div>
    </div>
  );
};

export default Register;