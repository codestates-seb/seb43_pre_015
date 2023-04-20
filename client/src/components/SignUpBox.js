import React, { useState } from 'react';
import styled from 'styled-components';
import '../App.css';

const SignUpBox = () => {
  return (
    <>
      <SignUpBoxContainer>
          <label for="display-name">Display Name</label>
          <input type="text" id="display-name"></input>
          <label for="email">Email</label>
          <input type="text" id="email"></input>
          <label for="password">Password</label>
          <input type="password" id="password"></input>
          <p>Passwords must contain at least eight characters. <br />including at least 1 letter and 1 number.</p>
          <p>Opt-in to receive occasional product updates,<br />user research invitations, company<br />announcements, and digests.</p>
          <button>Sign Up</button>
          <p>By clicking “Sign up”, you agree to our terms of<br />service, privacy policy and cookie policy</p>
      </SignUpBoxContainer>
    </>
  );
};

const SignUpBoxContainer = styled.div`  

    width: 318px;
    height: 600px;
    background-color: #fff;
    border-radius: 4px;
    border: solid 1px #666;
    display: flex;
    flex-flow: column wrap;
    justify-content: center;
    align-items: center;
    
    
    label {
        width: 268px;
        font-size: 0.9375rem;
        text-align: left;
        margin: 2px 0;
        padding: 0 2px;        
    }
    p {
        width: 268px;
        font-size: 0.75rem;
        text-align: left;
        color: #666;
    }

    input {
        width: 268px;
        height: 33px;
        background-color: #fff;
        border-radius: 5px;
        border: 1px solid #ccc;
    }
    button {
        width: 268px;
        height: 38px;
        background-color: #0095ff;
        border: 0px solid transparent;
        border-radius: 3px;
        box-shadow: rgba(255, 255, 255, .4) 0 1px 0 0 inset;
        box-sizing: border-box;
        color: #fff;
        cursor: pointer;
        display: inline-block;        
        font-size: 13px;
        font-weight: 400;
        line-height: 1.15385;
        margin: 0;
        outline: none;
        padding: 8px .8em;
        position: relative;
        text-align: center;
        text-decoration: none;
        user-select: none;
        -webkit-user-select: none;
        touch-action: manipulation;
        vertical-align: baseline;
        white-space: nowrap;
    }
    button:hover, button:focus {
        cursor: pointer;
        background-color: #07c;
    }
    button:focus {
        box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
    }
    button:active {
        background-color: #0064bd;
        box-shadow: none;
    }
`;

export default SignUpBox;
