import styled from 'styled-components';
import Sidebar from '../components/Sidebar.js'

function QuestionList() {
  return (
    <QuestionListPageBox>
      <QuestionListBox>
        <Sidebar />

        <QuestionMainBox>

        </QuestionMainBox>
      </QuestionListBox>
    </QuestionListPageBox>
  )
}

const QuestionListPageBox = styled.div`
  display: flex;
  justify-content: center;
  height: calc(100vh - 50px);
`

const QuestionListBox = styled.div`
  display: flex;
  width: 1264px;
  height: 100%;
`

const QuestionMainBox = styled.div`
  width: 1100px;
  background-color: blue;
`

export default QuestionList;