import React from 'react';
import styled from 'styled-components';
import SignUpBox from '../components/SignUpBox';
import { Link } from 'react-router-dom';

import { RiQuestionnaireFill } from 'react-icons/ri'
import { HiChevronUpDown } from "react-icons/hi2";
import { AiTwotoneTags } from "react-icons/ai";
import { AiFillTrophy } from "react-icons/ai";
import { FcGoogle } from "react-icons/fc";

const SignUpPage = () => {
  return (
    <>
      <SignUpContainer>
        <SignUpText>
          <h2>Join the Stackoverflow<br />community</h2>

          <div className="signup-text">
            <RiQuestionnaireFill className="signup-icon" /><p>Get Unstuck - Ask a Question</p>
          </div>
          <div className="signup-text">
            <HiChevronUpDown className="signup-icon" /><p>Unlock new privileges like voting and commenting</p>
          </div>
          <div className="signup-text">
            <AiTwotoneTags className="signup-icon" /><p>Save your favorite questions, answers, watch tags, and more</p>
          </div>
          <div className="signup-text">
            <AiFillTrophy className="signup-icon" /><p>Earn reputation and badges</p>
          </div>
          <p className="forteams-text">Collaborate and share knowledge with a private group for FREE.<br />
            Get Stack Overflow for Teams free for up to 50 users.</p>
        </SignUpText>

        <SignUpArea>
          <SignUpAuth>
            <button><FcGoogle size="18" className="auth-icon" />Sign up with Google</button>
          </SignUpAuth>
          <SignUpBox />
          <SignUpToLogin>
            <p>Already have an account? <Link to="/">Log in</Link></p>
            <p>Are you an employer? Sign up on Talent</p>
          </SignUpToLogin>
        </SignUpArea>

      </SignUpContainer>
    </>
  );
};

const SignUpContainer = styled.div`

  width: 100%;
  height: 100vh;
  background-color: #F1F2F3;
  box-sizing: border-box;
  font-family: -apple-system,system-ui,"Segoe UI","Liberation Sans",sans-serif;
  display: flex;
  flex-flow: row wrap;
  justify-content: center;
  align-items: center;

`;

const SignUpText = styled.div`

  width: 458px;
  height: 400px;

  h2 {
    font-size: 1.6875rem; 
    font-weight: 500;
    text-align: left;
  }

  .signup-text {    
    display: flex;
    justify-content: flex-start;
    align-items: center;
  }

  .signup-text>p {
    font-size: 0.9375rem; 
    font-weight: 300;
    text-align: left;
  }

  .signup-icon{
    width: 24px;
    height: 24px;
    color: #0095ff;
    margin-right: 10px;
  }

  .forteams-text {
    font-size: 0.8125rem; 
    font-weight: 300;
    text-align: left;
    color: #666;
  }

  

`;
const SignUpArea = styled.div`

`;

const SignUpAuth = styled.div`

  button {
    width: 318px;
    height: 40px;
    background-color: #fff;
    color: #333;
    border: 1px solid #ccc;
    border-radius: 4px;
    margin-bottom: 12px;
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  button:hover {
    background-color: #f5f5f5;
  }

  .auth-icon{
    margin-right: 5px;
  }
    
`;

const SignUpToLogin = styled.div`

  margin-top: 20px;

  p {
    width: 318px;
    font-size: 0.875rem; 
    font-weight: 300;
    text-align: center; 
  }
  
`;

export default SignUpPage;
