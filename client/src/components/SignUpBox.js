import React, { useState } from 'react';
import styled from 'styled-components';
import '../App.css';




const SignUpBox = () => {
  return (
    <>
      <SignUpBoxContainer>
          <h3>Display Name</h3>
          <input></input>
          <h3>Email</h3>
          <input></input>
          <h3>Password</h3>
          <input></input>
          <p>Passwords must contain at least eight characters. <br />including at least 1 letter and 1 number.</p>
          <p>Opt-in to receive occasional product updates,<br />user research invitations, company<br />announcements, and digests.</p>
          <button>Sign Up</button>
          <p>By clicking “Sign up”, you agree to our terms of<br />service, privacy policy and cookie policy</p>
      </SignUpBoxContainer>
    </>
  );
};

const SignUpBoxContainer = styled.div`  

    width: 400px;
    height: 600px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 3px 3px 3px #999;
    display: flex;
    flex-flow: column wrap;
    justify-content: center;
    align-items: center;
    
    
    h3 {
        width: 350px;
        text-align: left;
    }
    p {
        width: 350px;
        font-size: 0.875rem;
        text-align: left;
        color: #666;
    }

    input{
        width: 350px;
        height: 45px;
        background-color: #fff;
        border-radius: 5px;
        border: 1px solid #ccc;
    }
    button{
        width: 355px;
        height: 50px;
        background-color: #0A95FF;
        color: #fff;
        border-radius: 5px;
        border: none;
        margin-top: 15px;
    }
    button:hover{
        cursor: pointer;
        background-color: #006fc4;
    }
`;

export default SignUpBox;
