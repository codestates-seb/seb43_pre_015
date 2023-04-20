import "./App.css";
import Navbar from "./components/Navbar.js";
import LoginPage from "./components/LoginPage.js";
import Activity from "./pages/Activity";
import Sidebar from "./components/Sidebar";
import Profile from "./pages/Profile";
import QuestionDetail from "./pages/QuestionDetail";

function App() {
  return (
    <>
      <main>
        <Navbar />
        <section className="features">
          <QuestionDetail />
        </section>
      </main>
    </>
  );
}

export default App;
