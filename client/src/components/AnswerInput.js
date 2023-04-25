import React, { useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

import { AiOutlineBold } from "react-icons/ai";
import { AiOutlineItalic } from "react-icons/ai";
import { AiOutlineLink } from "react-icons/ai";
import { AiOutlineFileImage } from "react-icons/ai";

const AnswerInput = () => {
  return (
    <>
      <AnswerInputContainer>
        <InputTool>
          <button>
            <AiOutlineBold size="20" />
          </button>
          <button>
            <AiOutlineItalic size="20" />
          </button>
          <button>
            <AiOutlineLink size="20" />
          </button>
          <button>
            <AiOutlineFileImage size="20" />
          </button>
        </InputTool>
        <textarea type="text"></textarea>
        <AnswerSubmitBtn>Post Your Answer</AnswerSubmitBtn>
      </AnswerInputContainer>
    </>
  );
};

const AnswerInputContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;

  h3 {
    color: #232629;
    font-size: 1.2rem;
    font-weight: 400;
  }
  textarea {
    width: 664px;
    height: 210px;
    border: 1px solid #ccc;
    padding: 10px;
  }
  textarea:focus {
    box-shadow: 0 0 0 4px rgba(0, 149, 255, 0.15);
    border: 1px solid #59a4de;
    outline: none;
  }
`;

const InputTool = styled.div`
  width: 664px;
  height: 44px;
  border: 1px solid #ccc;
  border-bottom: none;
  padding: 0 8px;
  display: flex;
  justify-content: flex-start;
  align-items: center;

  button {
    border: none;
    background-color: transparent;
    width: 28px;
    height: 44px;
    cursor: pointer;
  }
`;

const AnswerSubmitBtn = styled(Link)`
  width: 122px;
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
  line-height: 1.15385;
  margin-top: 20px;
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

export default AnswerInput;
