import './App.css';
import styled from 'styled-components';
import Navbar from './components/Navbar.js';
// import LoginPage from './components/LoginPage.js';
import SignUpPage from './components/SignUpPage.js';

function App() {
  return (
    <AppBox>
      <Navbar></Navbar>
      {/* <LoginPage></LoginPage> */}
      <SignUpPage/>
    </AppBox>
  );
}

const AppBox = styled.div`
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
`

export default App;