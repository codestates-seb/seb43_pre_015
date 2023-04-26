import React, { useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import TextEditor from "../components/TextEditor";
import axios from "axios";

const AnswerInput = () => {
  const [answerContent, setAnswerContent] = useState("");

  const handleFormSubmit = async () => {
    console.log("Submitting form with values:", {
      content: `${answerContent}`,
      memberId: 1,
      questionId: 1,
    });

    try {
      const response = await axios.post(
        "https://proxy.cors.sh/https://a180-58-232-110-9.ngrok-free.app/api/answers",
        {
          content: `${answerContent}`,
          memberId: 1,
          questionId: 1,
        }
      );
      console.log("POST request successful:", response.data);
    } catch (error) {
      console.error("Error during POST request:", error);
    }
  };

  return (
    <>
      <AnswerInputContainer>
        <TextEditor handleContentChange={setAnswerContent} />

        <AnswerSubmitBtn onClick={() => handleFormSubmit()}>
          Post your answer
        </AnswerSubmitBtn>
      </AnswerInputContainer>
    </>
  );
};

const AnswerInputContainer = styled.div`
  margin-bottom: 30px;
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
  line-height: 1.15385rem;
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
