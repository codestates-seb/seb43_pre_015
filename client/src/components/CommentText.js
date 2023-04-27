import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";

const CommentText = () => {
  const [commentText, setCommentText] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/comments/1")
      .then((response) => {
        setCommentText(response.data.content);
        console.log(commentText);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <>
      <CommentTextContainer>
        <span>
          Got "undefined" console message by using
          Game.prototype.someFunctionName.
        </span>
        <span>{commentText}</span>
        <span> - </span>
        <a>lain-alice</a>
        <span className="comment-date">2 hours ago</span>
      </CommentTextContainer>
    </>
  );
};

const CommentTextContainer = styled.div`
  padding-bottom: 10px;
  border-bottom: 1px solid hsl(210, 8%, 90%);

  span {
    font-size: 0.8125rem;
    color: #232629;
  }
  a {
    font-size: 0.8125rem;
    color: #0074cc;
    cursor: pointer;
  }
  .comment-date {
    margin-left: 5px;
    color: #6a737c;
  }
`;
export default CommentText;
