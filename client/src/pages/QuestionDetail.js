import styled from "styled-components";
import UserProfile from "../components/UserProfile";
import Sidebar from "../components/Sidebar";

import { AiFillCaretUp } from "react-icons/ai";
import { AiFillCaretDown } from "react-icons/ai";
import { BsBookmark } from "react-icons/bs";
import { MdHistory } from "react-icons/md";

const QuestionDetail = () => {
  return (
    <QuestionDetailContainer>
      <Sidebar />
      <QuestionContainer>
        <QuestionTitle>
          <h2>
            How to vertically align text inside a textarea inside a flexbox
            inside a grid
          </h2>
          <button>Ask Question</button>
        </QuestionTitle>
        <QuestionDate>
          <p>Asked today</p>
          <p>Modified today</p>
          <p>Viewed 12 times</p>
        </QuestionDate>
        <QuestionMain>
          <QuestionLike>
            <div>
              <AiFillCaretUp />
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
          </QuestionLike>
          <article>
            <p>
              I want to center text inside a textarea inside a flexbox inside a
              grid. I tried hard to achieve this, but it seems to be impossible
              (for me). Fiddle:
            </p>
          </article>
        </QuestionMain>
        <QuestionEdit>
          <div className="question-edit">
            <div>Share</div>
            <div>Edit</div>
            <div>Follow</div>
          </div>
          <div className="question-profile">
            <p>Answered 18 years ago</p>

            <p>nickname</p>
          </div>
        </QuestionEdit>
      </QuestionContainer>
      <AnswerContainer></AnswerContainer>
    </QuestionDetailContainer>
  );
};

export default QuestionDetail;

const QuestionDetailContainer = styled.div`
  height: 100vh;
  display: flex;
  justify-content: center;
`;

const QuestionContainer = styled.div`
  width: 1100px;
  padding: 24px;
  display: flex;
  flex-direction: column;

  .question-title {
    color: #232629;
    font-size: 21px;
    margin-bottom: 8px;
  }
`;

const QuestionTitle = styled.div`
  display: flex;
  justify-content: space-between;

  h2 {
    font-size: 27px;
  }
  .question-date > p {
    margin-right: 15px;
  }
`;

const QuestionDate = styled.div`
  display: flex;
  justify-content: flex-start;
`;

const QuestionMain = styled.div`
  width: 1100px;
  display: flex;
  justify-content: flex-start;

  h2 {
    font-size: 27px;
  }
  .q-date-view {
    display: flex;
    justify-content: flex-start;
  }
  article {
    width: 620px;
    padding: 20px;
  }
`;

const QuestionLike = styled.div`
  width: 36px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
`;

const QuestionEdit = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align: center;
`;

const AnswerContainer = styled.div``;
