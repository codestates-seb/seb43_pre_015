import './App.css';
import styled from 'styled-components';
import Navbar from './components/Navbar.js';
import LoginPage from './components/LoginPage.js';

function App() {
  return (
    <AppBox>
      <Navbar></Navbar>
      <LoginPage></LoginPage>
    </AppBox>
  );
}

const AppBox = styled.div`
  display: flex;
  flex-direction: column;
`

export default App;
