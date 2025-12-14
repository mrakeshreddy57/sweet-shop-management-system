import React from "react";
import AuthService from "../services/auth.service";

const Dashboard = () => {
  const handleLogout = () => {
    AuthService.logout();
    window.location.href = "/login";
  };

  return (
    <div className="p-10 text-center">
       <h1 className="text-5xl font-bold text-white mb-8">🍬 Sweet Inventory</h1>
       <div className="glass-card p-10 inline-block">
          <p className="mb-4 text-xl">Welcome to the shop!</p>
          <button onClick={handleLogout} className="bg-red-400 text-white px-4 py-2 rounded shadow">Logout</button>
       </div>
    </div>
  );
};
export default Dashboard;