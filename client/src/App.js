import './App.css';
import styled from 'styled-components';
import Navbar from './components/Navbar.js';
import LoginPage from './components/LoginPage.js';
import SignUp from './components/SignUp.js';

function App() {
  return (
    <AppBox>
      <Navbar></Navbar>
      {/* <LoginPage></LoginPage> */}
      <SignUp/>
    </AppBox>
  );
}

const AppBox = styled.div`
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
`

export default App;