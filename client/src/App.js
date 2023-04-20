import './App.css';
import Navbar from './components/Navbar.js';
import LoginPage from './components/LoginPage.js';
import QuestionList from './components/QuestionList.js';

function App() {
  return (
    <>
      <main>
        <Navbar />
        <section className='features'>
          {/* <LoginPage /> */}
          <QuestionList />
        </section>
      </main>
    </>
  );
}

export default App;