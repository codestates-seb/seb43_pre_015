import "./App.css";
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar.js";
import LoginPage from "./pages/LoginPage.js";
import SignUpPage from "./pages/SignUpPage.js";
import Activity from "./pages/Activity";
import Profile from "./pages/Profile";
import QuestionList from "./pages/QuestionList.js";
import QuestionDetail from "./pages/QuestionDetail.js";
import Ask from "./pages/Ask.js";

function App() {
  return (
    <BrowserRouter>
      <main>
        <Navbar />
        <section className="features">
          <Routes>
            <Route path="/" element={<LoginPage />} />
            <Route path="/signup" element={<SignUpPage />} />
            <Route path="/questionlist" element={<QuestionList />} />
            <Route path="/questiondetail" element={<QuestionDetail />} />
            <Route path="/ask" element={<Ask />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/activity" element={<Activity />} />
          </Routes>
        </section>
      </main>
    </BrowserRouter>
  );
}

export default App;
