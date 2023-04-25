import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { ImPencil } from "react-icons/im";
import { HiAnnotation } from "react-icons/hi";
import { BsStackOverflow } from "react-icons/bs";
import { RiNumber4 } from "react-icons/ri";
import Sidebar from '../components/Sidebar.js'

function QuestionList() {
  return (
    <QuestionListPage>
      <QuestionContainer>
        <Sidebar />

        <QuestionMainBox>
          <QuestionListBox>
            <TitleBox>
              <Title>Top Questions</Title>
              <AskBtnBox>
                <AskBtn>Ask Question</AskBtn>
              </AskBtnBox>
            </TitleBox>
            <FilterBarBox>
              <FilterBar>
                <InterestingFilter>Interesting</InterestingFilter>
                <BountiedFilter>
                  <BountiedNumber>222</BountiedNumber>
                  Bountied
                </BountiedFilter>
                <HotFilter>Hot</HotFilter>
                <WeekFilter>Week</WeekFilter>
                <MonthFilter>Month</MonthFilter>
              </FilterBar>
            </FilterBarBox>

            <QuestionsBox>

              <QuestionsWrap>
                <Questions>
                  <QuestionState>
                    <Votes>0 votes</Votes>
                    <Answers>0 answers</Answers>
                    <Views>2 views</Views>
                  </QuestionState>
                  <QuestionContent>
                    <QuestionTitle>execution plan modification</QuestionTitle>
                    <QuestionAbout>
                      <QuestionTags>
                        <QuestionTag>sql-execution-plan</QuestionTag>
                        <QuestionTag>apache-age</QuestionTag>
                      </QuestionTags>
                      <QuestionCreater>
                        <CreaterName>Umer Freak</CreaterName>
                        <strong>1</strong>
                        <QuestionInfo> asked 31 secs ago</QuestionInfo>
                      </QuestionCreater>
                    </QuestionAbout>
                  </QuestionContent>
                </Questions>
              </QuestionsWrap>

              <QuestionsWrap>
                <Questions>
                  <QuestionState>
                    <Votes>0 votes</Votes>
                    <Answers>0 answers</Answers>
                    <Views>2 views</Views>
                  </QuestionState>
                  <QuestionContent>
                    <QuestionTitle>execution plan modification</QuestionTitle>
                    <QuestionAbout>
                      <QuestionTags>
                        <QuestionTag>sql-execution-plan</QuestionTag>
                        <QuestionTag>apache-age</QuestionTag>
                      </QuestionTags>
                      <QuestionCreater>
                        <CreaterName>Umer Freak</CreaterName>
                        <strong>1</strong>
                        <QuestionInfo> asked 31 secs ago</QuestionInfo>
                      </QuestionCreater>
                    </QuestionAbout>
                  </QuestionContent>
                </Questions>
              </QuestionsWrap>

            </QuestionsBox>

            <MoreText>
              Looking for more? Browse the <ToAllQuestions>complete list of questions</ToAllQuestions>, or <ToPopularTags>popular tags</ToPopularTags>. Help us answer <ToUnanswered>unanswered questions</ToUnanswered>.
            </MoreText>
          </QuestionListBox>

          <QuestionAside>
            <QuestionAsideBox>
              <FirstAside>
                <OverflowBlogTitle>The Overflow Blog</OverflowBlogTitle>
                <OverflowBlogContent>
                  <OverflowBlogIcon><ImPencil /></OverflowBlogIcon>
                  <OverflowBlogElement>When setting up monitoring, less data is better (Ep. 563)</OverflowBlogElement>
                </OverflowBlogContent>
                <FeaturedTitle>Featured on Meta</FeaturedTitle>
                <FeaturedContent>
                  <FeaturedIcon className="bubble"><HiAnnotation /></FeaturedIcon>
                  <FeaturedElement>Improving the copy in the close modal and post notices - 2023 edition</FeaturedElement>
                </FeaturedContent>
                <FeaturedContent>
                  <FeaturedIcon className="bubble"><HiAnnotation /></FeaturedIcon>
                  <FeaturedElement>New blog post from our CEO Prashanth: Community is the future of AI</FeaturedElement>
                </FeaturedContent>
                <FeaturedContent>
                  <FeaturedIcon className="overflow"><BsStackOverflow /></FeaturedIcon>
                  <FeaturedElement>Temporary policy: ChatGPT is banned</FeaturedElement>
                </FeaturedContent>
                <FeaturedContent>
                  <FeaturedIcon className="overflow"><BsStackOverflow /></FeaturedIcon>
                  <FeaturedElement>The [protection] tag is being burninated</FeaturedElement>
                </FeaturedContent>
                <FeaturedContent>
                  <FeaturedIcon className="overflow"><BsStackOverflow /></FeaturedIcon>
                  <FeaturedElement>Content Discovery initiative April 13 update: Related questions using a...</FeaturedElement>
                </FeaturedContent>
                <FeaturedContent>
                  <FeaturedIcon className="overflow"><BsStackOverflow /></FeaturedIcon>
                  <FeaturedElement>Review our technical responses for the 2023 Developer Survey</FeaturedElement>
                </FeaturedContent>
                <HotPostTitle>Hot Meta Posts</HotPostTitle>
                <HotPostContent>
                  <HotPostIcon><RiNumber4 /></HotPostIcon>
                  <HotPostElement>Are questions about the security implications of design choices considered...</HotPostElement>
                </HotPostContent>
              </FirstAside>

              <SecondAside>
                <SecondAsideTitle>Custom Filters</SecondAsideTitle>
                <SecondAsideContent>
                  <SecondAsideLink>Create a custom Filter</SecondAsideLink>
                </SecondAsideContent>
              </SecondAside>

              <ThirdAside>
                <ThirdAsideTitleBox>
                  <ThirdAsideTitle>Watched Tags</ThirdAsideTitle>
                  <EditBtn>edit</EditBtn>
                </ThirdAsideTitleBox>
                <ThirdAsideContent>
                  <ThirdAsideTags>
                    <ThirdAsideTag>javascript</ThirdAsideTag>
                  </ThirdAsideTags>
                </ThirdAsideContent>
              </ThirdAside>

              <FourthAside>
                <FourthAsideTitle>Ignored Tags</FourthAsideTitle>
                <FourthAsideContent>
                  <FourthAsideAddBtn>Add an ignored tag</FourthAsideAddBtn>
                </FourthAsideContent>
              </FourthAside>

            </QuestionAsideBox>
          </QuestionAside>
        </QuestionMainBox>
      </QuestionContainer>
    </QuestionListPage>
  )
}

const QuestionListPage = styled.div`
  display: flex;
  justify-content: center;
  height: calc(100vh - 50px);
`

const QuestionContainer = styled.div`
  display: flex;
  width: 1264px;
  height: 100%;
`

const QuestionMainBox = styled.div`
  width: 1100px;
  display: flex;
`

const QuestionListBox = styled.div`
  width: 800px;
  padding: 30px 30px 30px 0;
`

const TitleBox = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 30px;
  padding-left: 30px;
`

const AskBtn = styled(Link)`
  background-color: #0095ff;
  border: 1px solid transparent;
  border-radius: 3px;
  box-shadow: rgba(255, 255, 255, .4) 0 1px 0 0 inset;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  display: flex;
  align-items: center;
  font-family: -apple-system,system-ui,"Segoe UI","Liberation Sans",sans-serif;
  font-size: 13px;
  font-weight: 400;
  line-height: 1.15385;
  margin: 0;
  outline: none;
  padding: 8px .8em;
  position: relative;
  text-align: center;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  vertical-align: baseline;
  white-space: nowrap;
  height: 38px;
  

  &:hover,
  &:focus {
  background-color: #07c;
  }

  &:focus {
  box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
  }

  &:active {
  background-color: #0064bd;
  box-shadow: none;
  }
`

const AskBtnBox = styled.div``

const Title = styled.h1`
  font-size: 2rem;
  margin: 0;
`

const FilterBarBox = styled.div`
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
`

const FilterBar = styled.div`
  display: flex;
  height: 40px;
  border-radius: 5px;
  border: 1px solid hsl(210, 8%, 65%);
  font-size: 13px;
`

const InterestingFilter = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
  border-right: 1px solid hsl(210, 8%, 65%);
  padding: .8em;
  cursor: pointer;
`

const BountiedFilter = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
  border-right: 1px solid hsl(210, 8%, 65%);
  padding: .8em;
  cursor: pointer;
`

const BountiedNumber = styled.div`
  background-color: hsl(206, 100%, 40%);
  color: hsl(0, 0%, 100%);
  border-radius: 2px;
  padding: 0.2em 0.5em 0.25em;
  margin-right: 5px;
  font-size: 11px;
`

const HotFilter = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
  border-right: 1px solid hsl(210, 8%, 65%);
  padding: .8em;
  cursor: pointer;
`

const WeekFilter = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
  border-right: 1px solid hsl(210, 8%, 65%);
  padding: .8em;
  cursor: pointer;
`

const MonthFilter = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
  padding: .8em;
  cursor: pointer;
`

const QuestionsBox = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 60px;
`

const QuestionsWrap = styled.div`
  border-top: 1px solid hsl(210, 8%, 90%);
  height: 106px;
`

const Questions = styled.div`
  display: flex;
  padding: 16px;
  height: 100%;
`

const QuestionState = styled.div`
  display: flex;
  flex-direction: column;
  width: 108px;
  justify-content: space-between;
  align-items: end;
  margin-right: 16px;
  margin-bottom: 4px;
  font-size: 14px;
`

const Votes = styled.div`
  color: hsl(210, 8%, 5%);
`

const Answers = styled.div`
  color: hsl(210, 8%, 45%);
`

const Views = styled.div`
  color: hsl(210, 8%, 45%);
`

const QuestionContent = styled.div`
  width: 100%;
`

const QuestionTitle = styled.div`
  color: hsl(206, 100%, 40%);
  font-size: 1.30769231rem;
  margin-bottom: 5px;
  padding-right: 24px;
`


const QuestionAbout = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
`

const QuestionTags = styled.div`
  display: flex;
  margin-bottom: 13px;
`

const QuestionTag = styled(Link)`
  background-color: hsl(205, 46%, 92%);
  border-radius: 3px;
  color: hsl(209, 100%, 26%);
  font-size: 12px;
  padding: 0.4em 0.5em;
  margin-right: 4px;
  text-decoration: none;

  &:last-of-type {
    margin-right: 0;
  }
`

const QuestionCreater = styled.div`
  display: flex;
  font-size: 12px;
  justify-content: space-between;
  width: 182px;
`

const CreaterName = styled(Link)`
  color: hsl(206, 100%, 40%);
  text-decoration: none;
`

const QuestionInfo = styled(Link)`
  color: hsl(210, 8%, 25%);
  text-decoration: none;
`

const MoreText = styled.div`
  padding: 0 0 60px 30px;
  line-height: 1.4;
`

const ToAllQuestions = styled(Link)`
  text-decoration: none;
  color: hsl(206, 100%, 40%);
`

const ToPopularTags = styled(Link)`
  text-decoration: none;
  color: hsl(206, 100%, 40%);
`

const ToUnanswered = styled(Link)`
  text-decoration: none;
  color: hsl(206, 100%, 40%);
`

const QuestionAside = styled.div`
  width: 300px;
  padding-top: 30px;
`

const QuestionAsideBox = styled.div`
  height: 100%;
`

const FirstAside = styled.div`
  background-color: #fdf7e2;
  width: 100%;
  border: 1px solid hsl(47, 65%, 84%);
  border-radius: 3px;
  box-shadow: 0 1px 2px hsla(0,0%,0%,0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05), 0 2px 8px hsla(0, 0%, 0%, 0.05);
  margin-bottom: 16px;
`

const OverflowBlogTitle = styled.div`
  background-color: hsl(47, 83%, 91%);
  width: 100%;
  height: 42px;
  font-size: 12px;
  padding: 12px 15px;
  display: flex;
  align-items: center;
  font-weight: bold;
  border-bottom: 1px solid hsl(47, 65%, 84%);
`

const OverflowBlogContent = styled.div`
  padding: 0 16px;
  margin: 12px 0;
  display: flex;
`

const OverflowBlogIcon = styled.div`
  width: 22px;
  height: 34px;
  margin-right: 5px;

  & svg {
    width: 12px;
    height: 12px;
  }
`

const OverflowBlogElement = styled.div`
  font-size: 12px;
`

const FeaturedTitle = styled.div`
  background-color: hsl(47, 83%, 91%);
  width: 100%;
  height: 42px;
  font-size: 12px;
  padding: 12px 15px;
  display: flex;
  align-items: center;
  font-weight: bold;
  border-top: 1px solid hsl(47, 65%, 84%);
  border-bottom: 1px solid hsl(47, 65%, 84%);
`

const FeaturedContent = styled.div`
  padding: 0 16px;
  margin: 12px 0;
  display: flex;
`

const FeaturedIcon = styled.div`
  width: 22px;
  height: 34px;
  margin-right: 5px;

  &.overflow {
    width: 14px;
  }

  &.bubble svg {
    width: 14px;
    height: 14px;
    color: hsl(206, 930%, 69.5%);
  }

  &.overflow svg {
    width: 13px;
    height: 13px;
  }
`

const FeaturedElement = styled.div`
  font-size: 12px;
`

const HotPostTitle = styled.div`
  background-color: hsl(47, 83%, 91%);
  width: 100%;
  height: 42px;
  font-size: 12px;
  padding: 12px 15px;
  display: flex;
  align-items: center;
  font-weight: bold;
  border-top: 1px solid hsl(47, 65%, 84%);
  border-bottom: 1px solid hsl(47, 65%, 84%);
`

const HotPostContent = styled.div`
  padding: 0 16px;
  margin: 12px 0;
  display: flex;
`

const HotPostIcon = styled.div`
  width: 22px;
  height: 34px;
  margin-right: 5px;

  & svg {
    width: 12px;
    height: 12px;
  }
`

const HotPostElement = styled.div`
  font-size: 12px;
`

const SecondAside = styled.div`
  border-radius: 3px;
  border: 1px solid hsl(210, 8%, 90%);
  box-shadow: 0 1px 2px hsla(0,0%,0%,0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05), 0 2px 8px hsla(0, 0%, 0%, 0.05);
  margin-bottom: 16px;
`

const SecondAsideTitle = styled.div`
  padding: 12px 15px;
  height: 45px;
  background-color: hsl(210,8%,97.5%);
  font-size: 14px;
  border-bottom: 1px solid hsl(210, 8%, 90%);
`

const SecondAsideContent = styled.div`
  padding: 16px 15px;
`

const SecondAsideLink = styled(Link)`
  color: hsl(206,100%,40%);
  text-decoration: none;
  font-size: 13px;
`

const ThirdAside = styled.div`
  border-radius: 3px;
  border: 1px solid hsl(210, 8%, 90%);
  box-shadow: 0 1px 2px hsla(0,0%,0%,0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05), 0 2px 8px hsla(0, 0%, 0%, 0.05);
  margin-bottom: 16px;
`

const ThirdAsideTitleBox = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 12px 15px;
  height: 45px;
  background-color: hsl(210,8%,97.5%);
  font-size: 14px;
  border-bottom: 1px solid hsl(210, 8%, 90%);
`

const ThirdAsideTitle = styled.div``

const EditBtn = styled.div`
  cursor: pointer;
  color: hsl(210,8%,45%);
`

const ThirdAsideContent = styled.div``

const ThirdAsideTags = styled.div`
  padding: 16px 15px;
`

const ThirdAsideTag = styled(Link)`
  font-size: 12px;
  text-decoration: none;
  padding: 4.8px 6px;
  background-color: hsl(205,46%,92%);
  color: hsl(205,47%,42%);
  border-radius: 3px;
`

const FourthAside = styled.div`
  border-radius: 3px;
  border: 1px solid hsl(210, 8%, 90%);
  box-shadow: 0 1px 2px hsla(0,0%,0%,0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05), 0 2px 8px hsla(0, 0%, 0%, 0.05);
`

const FourthAsideTitle = styled.div`
  padding: 12px 15px;
  height: 45px;
  background-color: hsl(210,8%,97.5%);
  font-size: 14px;
  border-bottom: 1px solid hsl(210, 8%, 90%);
`

const FourthAsideContent = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 16px 15px;
`

const FourthAsideAddBtn = styled.button`
  border: none;
  background-color: hsl(205,46%,92%);
  border: 1px solid hsl(205,41%,63%);
  color: hsl(205,47%,42%);
  padding: 9.6px;
  border-radius: 3px;
`

export default QuestionList;