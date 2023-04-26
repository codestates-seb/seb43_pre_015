import React, { useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

import { ImCheckboxUnchecked } from "react-icons/im";
import { ImCheckboxChecked } from "react-icons/im";

const SignUpBox = () => {
  const [checked, setChecked] = useState(false);

  return (
    <>
      <SignUpBoxContainer>
        <label for="display-name">Display Name</label>
        <input type="text" id="display-name"></input>
        <label for="email">Email</label>
        <input type="text" id="email"></input>
        <label for="password">Password</label>
        <input type="password" id="password" className="password-input"></input>
        <p>
          Passwords must contain at least eight characters. <br />
          including at least 1 letter and 1 number.
        </p>
        <SignUpCheckbox>
          <CheckBox
            onClick={() => {
              setChecked(!checked);
            }}
          >
            {checked ? <ImCheckboxUnchecked /> : <ImCheckboxChecked />}
          </CheckBox>
          <span className="checkbox-text">
            Opt-in to receive occasional product updates,
            <br />
            user research invitations, company
            <br />
            announcements, and digests.
          </span>
        </SignUpCheckbox>
        <SignUpSubmitBtn to="/">Sign Up</SignUpSubmitBtn>
        <p>
          By clicking “Sign up”, you agree to our terms of
          <br />
          service, privacy policy and cookie policy
        </p>
      </SignUpBoxContainer>
    </>
  );
};

const SignUpBoxContainer = styled.div`
  width: 318px;
  height: 600px;
  background-color: #fff;
  border-radius: 4px;
  border: none;
  box-shadow: 3px 3px 5px #999;
  display: flex;
  flex-flow: column wrap;
  justify-content: center;
  align-items: center;

  label {
    width: 268px;
    font-size: 0.9375rem;
    text-align: left;
    font-weight: 600;
    margin-bottom: 10px;
  }
  p {
    width: 268px;
    font-size: 0.75rem;
    text-align: left;
    color: #666;
  }
  input {
    width: 268px;
    height: 35px;
    background-color: #fff;
    border-radius: 4px;
    border: 1px solid #ccc;
    margin-bottom: 20px;
    padding: 8px;
  }
  input:focus {
    box-shadow: 0 0 0 4px rgba(0, 149, 255, 0.15);
    border: 1px solid #59a4de;
    outline: none;
  }
  .password-input {
    margin-bottom: 0;
  }
`;

const SignUpCheckbox = styled.div`
  width: 268px;
  padding: 10px 0 20px;
  display: flex;
  justify-content: center;
  align-items: flex-start;

  .checkbox-text {
    font-size: 0.8125rem;
    line-height: 16px;
    text-align: left;
    color: #333;
  }
`;

export const CheckBox = styled.button`
  border: none;
  background-color: rgba(255, 255, 255, 0);
  color: #838c95;
  cursor: pointer;

  &:hover {
    color: #0074cc;
  }
`;

const SignUpSubmitBtn = styled(Link)`
  width: 268px;
  height: 38px;
  background-color: #0095ff;
  border: 1px solid transparent;
  border-radius: 3px;
  box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0 0 inset;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  display: inline-block;
  font-size: 13px;
  font-weight: 400;
  line-height: 1.15385rem;
  margin: 0;
  outline: none;
  padding: 8px 0.8em;
  position: relative;
  text-align: center;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  vertical-align: baseline;
  white-space: nowrap;

  &:hover,
  &:focus {
    cursor: pointer;
    background-color: #07c;
  }

  &:focus {
    box-shadow: 0 0 0 4px rgba(0, 149, 255, 0.15);
  }

  &:active {
    background-color: #0064bd;
    box-shadow: none;
  }
`;

export default SignUpBox;
