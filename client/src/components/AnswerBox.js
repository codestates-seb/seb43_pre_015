import React, { useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
import CommentInput from "./CommentInput";
import CommentText from "./CommentText";

import { AiFillCaretUp } from "react-icons/ai";
import { AiFillCaretDown } from "react-icons/ai";
import { GrBookmark } from "react-icons/gr";
import { GrHistory } from "react-icons/gr";

const AnswerBox = ({ answerContent }) => {
  const [visible, setVisible] = useState(false);

  return (
    <>
      <AnswerBoxContainer>
        <QnaLike>
          <a className="like-icon">
            <AiFillCaretUp size="36" color="#babfc4" />
          </a>
          <div className="like-count">-1</div>
          <a className="like-icon">
            <AiFillCaretDown size="36" color="#babfc4" />
          </a>
          <a className="like-icon">
            <GrBookmark size="18" color="#babfc4" />
          </a>
          <a className="like-icon">
            <GrHistory size="18" color="#babfc4" />
          </a>
        </QnaLike>
        <QnaMain>
          <article>
            <p>answer test</p>
            <p>{answerContent}</p>
          </article>
          <QnaTag>
            <a className="qna-tag">javascript</a>
            <a className="qna-tag">React</a>
            <a className="qna-tag">closures</a>
          </QnaTag>
          <QnaEditContainer>
            <div className="qna-edit">
              <div>Share</div>
              <div>Edit</div>
              <div>Follow</div>
            </div>
            <QnaProfile>
              <div className="profile-date">answered 18 hours ago</div>
              <div className="profile-user">
                <img
                  className="profile-userimg"
                  alt="profile"
                  src="/img/profile.png"
                />
                <a className="profile-username">lain-alice</a>
              </div>
            </QnaProfile>
          </QnaEditContainer>
          <QnaComment>
            <CommentText />
            <CommentButton
              onClick={() => {
                setVisible(!visible);
              }}
            >
              Add a comment
            </CommentButton>
            {visible && <CommentInput />}
          </QnaComment>
        </QnaMain>
      </AnswerBoxContainer>
    </>
  );
};

const AnswerBoxContainer = styled.div`
  width: 1100px;
  display: flex;
  justify-content: flex-start;
`;

const QnaMain = styled.div`
  border-bottom: 1px solid hsl(210, 8%, 90%);

  article {
    width: 664px;
    padding: 20px;
  }
`;

const QnaTag = styled.div`
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding-left: 20px;

  .qna-tag {
    height: 24px;
    color: #39739d;
    font-size: 0.75rem;
    border-radius: 4px;
    background-color: #e1ecf4;
    margin: 2px 8px 2px 0;
    padding: 4.8px 6px;
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
  }
`;

const QnaLike = styled.div`
  width: 40px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  text-align: center;

  .like-icon {
    width: 36px;
    padding: 4px 0;
    cursor: pointer;
  }
  .like-count {
    width: 36px;
    font-size: 1.3125rem;
    color: #6a737c;
    vertical-align: middle;
    padding: 4px 0;
  }
`;

const QnaEditContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align: center;
  padding: 20px;

  .qna-edit {
    display: flex;
    justify-content: center;
  }
  .qna-edit > div {
    text-align: left;
    font-size: 0.875rem;
    font-weight: 300;
    padding-right: 30px;
    cursor: pointer;
  }
`;

const QnaProfile = styled.div`
  background-color: #d0e3f1;
  width: 200px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  padding: 5px 6px 7px 7px;

  .profile-date {
    font-size: 0.75rem;
    color: #6a737c;
    font-weight: 300;
    margin: 1px 0px 4px;
  }
  .profile-user {
    display: flex;
    justify-content: center;
    align-items: flex-start;
  }
  .profile-userimg {
    width: 32px;
  }
  .profile-username {
    font-size: 0.8125rem;
    color: #0074cc;
    font-weight: 300;
    margin-left: 8px;
    cursor: pointer;
  }
`;

const QnaComment = styled.div``;

const CommentButton = styled.button`
  border: none;
  background-color: rgba(255, 255, 255, 0);
  color: #838c95;
  font-size: 0.8125rem;
  cursor: pointer;
  margin: 8px 0 10px;

  &:hover {
    color: #0074cc;
  }
`;

export default AnswerBox;
