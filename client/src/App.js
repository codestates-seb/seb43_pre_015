import './App.css';
import styled from 'styled-components';
import Navbar from './components/Navbar.js';
import LoginPage from './components/LoginPage.js';

function App() {
  return (
    <div>
      <Navbar></Navbar>
      <LoginPage></LoginPage>
    </div>

  );
}

const AppBox = styled.div`
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
`

export default App;