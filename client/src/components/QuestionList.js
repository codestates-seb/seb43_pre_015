import styled from 'styled-components';

function QuestionList() {
  return (
    <QuestionListPageBox>
      <Sidebar></Sidebar>
    </QuestionListPageBox>
  )
}

const QuestionListPageBox = styled.div`
  display: flex;
`

export default QuestionList();