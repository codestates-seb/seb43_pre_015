import styled from "styled-components";
import UserProfile from "../components/UserProfile";
import Sidebar from "../components/Sidebar";

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
          <div className="q-date-view">
            <p>Asked today</p>
            <p>Modified today</p>
            <p>Viewed 12 times</p>
          </div>
        </QuestionTitle>
      </QuestionContainer>
      <AnswerContainer></AnswerContainer>
    </QuestionDetailContainer>
  );
};

export default QuestionDetail;

export const QuestionDetailContainer = styled.div`
  display: flex;
  justify-content: center;
  height: 100vh;
`;

export const QuestionContainer = styled.div`
  // margin-top: 50px; // for the header
  padding: 24px;
  width: 1100px;
  display: flex;
  flex-direction: column;

  .question-title {
    color: #232629;
    font-size: 21px;
    margin-bottom: 8px;
  }
`;

export const QuestionTitle = styled.div`
  h2 {
    font-size: 27px;
  }
  .q-date-view {
    display: flex;
    justify-content: flex-start;
  }
`;

export const AnswerContainer = styled.div``;
