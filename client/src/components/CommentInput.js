import React, { useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";

const CommentInput = () => {
  const [commentContent, setCommentContent] = useState("");

  const handleFormSubmit = async () => {
    console.log("Submitting form with values:", {
      content: `${commentContent}`,
      memberId: 1,
      questionId: 1,
    });

    try {
      const response = await axios.post(
        "https://a180-58-232-110-9.ngrok-free.app/api/comments",
        {
          memberId: 1,
          questionId: 1,
          content: `${commentContent}`,
        }
      );
      console.log("POST request successful:", response.data);
    } catch (error) {
      console.error("Error during POST request:", error);
    }
  };

  return (
    <>
      <CommentInputContainer>
        <input
          type="text"
          placeholder="Add a comment"
          value={commentContent}
          onChange={(e) => {
            setCommentContent(e.target.value);
          }}
        />
        <CommentSubmitBtn onClick={() => handleFormSubmit()}>
          Send!
        </CommentSubmitBtn>
      </CommentInputContainer>
    </>
  );
};

const CommentInputContainer = styled.div`
  input {
    height: 35px;
    border: 1px solid #ccc;
    margin-bottom: 10px;
    padding: 4px 200px 4px 10px;
  }

  input:focus {
    box-shadow: 0 0 0 4px rgba(0, 149, 255, 0.15);
    border: 1px solid #59a4de;
    outline: none;
  }
`;
const CommentSubmitBtn = styled(Link)`
  width: 50px;
  height: 35px;
  background-color: #0095ff;
  border: 1px solid transparent;
  box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0 0 inset;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  font-size: 13px;
  font-weight: 400;
  line-height: 1.15385;
  margin-left: 10px;
  outline: none;
  padding: 8px;
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
export default CommentInput;
