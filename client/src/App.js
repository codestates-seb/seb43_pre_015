import './App.css';
import Navbar from './components/Navbar.js';
import LoginPage from './components/LoginPage.js';

function App() {
  return (
    <>
      <main>
        <Navbar />
        <section className='features'>
          <LoginPage />
        </section>
      </main>
    </>
  );
}

export default App;