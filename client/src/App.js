import './App.css';
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar.js';
import Sidebar from './components/Sidebar';
import LoginPage from './pages/LoginPage.js';
import SignUpPage from './pages/SignUpPage.js';
import Activity from './pages/Activity';
import Profile from './pages/Profile';
import QuestionList from './pages/QuestionList.js';
import Ask from './pages/Ask';

function App() {
  return (
    <BrowserRouter>
      <main>
        <Navbar />
        <section className='features'>
          {/* <Routes>
            <Route path="/" element={<LoginPage />} />
            <Route path="/signup" element={<SignUpPage />} />
            <Route path="/questionlist" element={<QuestionList />} />
          </Routes> */}
          <Ask />
        </section>
      </main>


    </BrowserRouter>
  );
}

export default App;