import './App.css';
import Navbar from './components/Navbar.js';
import LoginPage from './components/LoginPage.js';
import SignUpPage from './components/SignUpPage';

function App() {
  return (
    <>
      <main>
        <Navbar />
        <section className='features'>
          <SignUpPage />
        </section>
      </main>
    </>
  );
}

export default App;