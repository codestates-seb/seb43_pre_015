import './App.css';
import styled from 'styled-components';
import Navbar from './components/Navbar.js';
import LoginPage from './components/LoginPage.js';

function App() {
  return (
    <div>
      <main>
        <Navbar />
        <section className='features'>
          <LoginPage />
        </section>
      </main>
    </div>
  );
}

export default App;