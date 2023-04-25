import styled from "styled-components";
import { Link } from "react-router-dom";
import Sidebar from "../components/Sidebar";
import QnaBox from "../components/QnaBox";
import AnswerInput from "../components/AnswerInput";

const QuestionDetail = () => {
  return (
    <QuestionDetailContainer>
      <Sidebar />
      <QnaContainer>
        <QuestionContainer>
          <QuestionTitle>
            <h2>How to access the internal function?</h2>
            <AskQuestionButton to="/">Ask Question</AskQuestionButton>
          </QuestionTitle>
          <QuestionDate>
            <div>Asked today</div>
            <div>Modified today</div>
            <div>Viewed 12 times</div>
          </QuestionDate>
          <QnaBox />
        </QuestionContainer>
        <AnswerContainer>
          <AnswerTitle>
            <h3>2 Answer</h3>
            <div>sorted by:</div>
          </AnswerTitle>
          <QnaBox />
          <AnswerTitle>
            <h3>Your Answer</h3>
          </AnswerTitle>
          <AnswerInput />
        </AnswerContainer>
      </QnaContainer>
    </QuestionDetailContainer>
  );
};

export default QuestionDetail;

const QuestionDetailContainer = styled.div`
  height: 100vh;
  display: flex;
  justify-content: center;
`;
const QnaContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const QuestionContainer = styled.div`
  width: 1100px;
  padding: 24px;
  display: flex;
  flex-direction: column;
`;

const QuestionTitle = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;

  h2 {
    color: #232629;
    font-size: 1.6875rem;
    font-weight: 400;
  }
  .question-date > p {
    margin-right: 14px;
  }
`;

const QuestionDate = styled.div`
  display: flex;
  justify-content: flex-start;
  padding-bottom: 20px;
  border-bottom: 1px solid hsl(210, 8%, 90%);

  div {
    text-align: left;
    font-size: 0.875rem;
    font-weight: 300;
    padding-right: 30px;
  }
`;

const AskQuestionButton = styled(Link)`
  width: 99px;
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
  outline: none;
  padding: 8px 0.8em;
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

const AnswerContainer = styled.div`
  width: 1100px;
  padding: 24px;
  display: flex;
  flex-direction: column;
`;

const AnswerTitle = styled.div`
  width: 696px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  h3 {
    color: #232629;
    font-size: 1.2rem;
    font-weight: 400;
  }
`;
