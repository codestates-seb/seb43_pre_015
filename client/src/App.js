import "./App.css";
import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar.js";
import Sidebar from "./components/Sidebar";
import LoginPage from "./pages/LoginPage.js";
import SignUpPage from "./pages/SignUpPage.js";
import Activity from "./pages/Activity";
import Profile from "./pages/Profile";
import QuestionList from "./pages/QuestionList.js";
import QuestionDetail from "./pages/QuestionDetail.js";
import Ask from './pages/Ask';
import Tag from './components/Tag';
import DiscardModal from './components/DiscardModal';


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
          </Routes>
        </section>
      </main>
    </BrowserRouter>
  );
}

export default App;
