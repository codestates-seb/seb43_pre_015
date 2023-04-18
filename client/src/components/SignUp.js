import React from 'react';
import styled from 'styled-components';
import '../App.css';
import SignUpBox from './SignUpBox';

const SignUp = () => {
  return (
    <>
      <SignUpContainer>        
        <SignUpText>
            <h2>Join the Stackoverflow Community</h2>
            <p>Get Unstuck - Ask a Question</p>                
            <p>Unlock new privileges like voting and commenting</p>
            <p>Save your favorite questions, answers, watch tags, and more</p>
            <p>Earn reputation and badges</p>
        </SignUpText>

        <SignUpArea>
          <SignUpBox />
          <SignUpToLogin>
            <p>Already have an account? <a href="#">Log in</a></p>
          </SignUpToLogin>
        </SignUpArea>

      </SignUpContainer>
    </>
  );
};

const SignUpContainer = styled.div`

  width: 100%;
  height: 100vh;
  display: flex;
  flex-flow: row wrap;
  justify-content: space-around;
  align-items: center;
  background-color: #F1F2F3;

`;
  
const SignUpText = styled.div`

  h2 {
    font-size: 1.75rem; 
    font-weight: 700;
    text-align: left;
  }

  p {
    font-size: 1rem; 
    font-weight: 500;
    text-align: left;
  }

`;
const SignUpArea = styled.div`
  
  
`;

const SignUpToLogin = styled.div`

  margin-top: 20px;

  p {
    width: 400px;
    font-size: 0.875rem;
    font-weight: 500;
    text-align: center;    
  }
  
`;

export default SignUp;
