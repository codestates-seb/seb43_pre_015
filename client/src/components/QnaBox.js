import React, { useState } from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

import { AiFillCaretUp } from "react-icons/ai";
import { AiFillCaretDown } from "react-icons/ai";
import { BsBookmark } from "react-icons/bs";
import { MdHistory } from "react-icons/md";

const QnaBox = () => {
  return (
    <>
      <QnaBoxContainer>
        <QnaLike>
          <div>
            <AiFillCaretUp className="qna" />
          </div>
          <div>-1</div>
          <div>
            <AiFillCaretDown />
          </div>
          <div>
            <BsBookmark />
          </div>
          <div>
            <MdHistory />
          </div>
        </QnaLike>
        <QnaText>
          <article>
            <p>
              I want to center text inside a textarea inside a flexbox inside a
              grid. I tried hard to achieve this, but it seems to be impossible
              (for me). Fiddle:
            </p>
          </article>
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
                <div className="profile-username">lain-alice</div>
              </div>
            </QnaProfile>
          </QnaEditContainer>
        </QnaText>
      </QnaBoxContainer>
    </>
  );
};

const QnaBoxContainer = styled.div`
  width: 1100px;
  display: flex;
  justify-content: flex-start;
`;

const QnaText = styled.div`
  article {
    width: 664px;
    padding: 20px;
  }
`;

const QnaLike = styled.div`
  width: 36px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  text-align: center;
`;

const QnaEditContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align: center;
  padding: 20px;
  border-bottom: 1px solid hsl(210, 8%, 90%);

  .qna-edit {
    display: flex;
    justify-content: center;
  }
  .qna-edit > div {
    text-align: left;
    font-size: 0.875rem;
    font-weight: 300;
    padding-right: 30px;
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
  }
`;

export default QnaBox;
