import React, { useState } from 'react';
import styled from 'styled-components';
import TextEditor from '../components/TextEditor';
import { RegistLink } from '../components/Navbar';

function Ask() {
    const [focusedElement, setFocusedElement] = useState(null);

    return (
        <AskPage>
            <AskContainer>
                <HeadContainer>
                    <h1>Ask a public question</h1>
                    <img className='robot-img' alt='curious-robot' src='./img/ask.png'/>
                </HeadContainer>
                <GuideBox>
                    <h2>Writing a good question</h2>
                    <p>You're ready to <a href="https://stackoverflow.com/help/how-to-ask">ask</a> a <a href="https://stackoverflow.com/help/on-topic">programming-related question</a> and this form will help guide you through the process.</p>
                    <p className='p2'>Looking to ask a non-programming question? See <a href="https://stackexchange.com/sites#technology-traffic">the topics here</a> to find a relevant site.</p>
                    <h5>Steps</h5>
                    <ul>
                        <li>Summarize your problem in a one-line title.</li>
                        <li>Describe your problem in more detail.</li>
                        <li>Describe what you tried and what you expected to happen.</li>
                        <li>Add “tags” which help surface your question to members of the community.</li>
                        <li>Review your question and post it to the site.</li>
                    </ul>
                </GuideBox>
                <FormContainer>
                    <ContentBox>
                        <div className='label-box'>
                            <label className='title'>Title</label>
                            <label className='description'>Be specific and imagine you're asking a question to another person.</label>
                        </div>
                        <input 
                            type='text' 
                            maxlength='300' 
                            placeholder='e.g. Is there an R function for finding the index of an element in a vector?' 
                            onFocus={() => setFocusedElement('title')} 
                        />
                        <NextBtn>Next</NextBtn>
                    </ContentBox>
                    {focusedElement === 'title' && (
                    <FirstTipBox>
                        <div className='tip-title'>Writing a good title</div>
                        <div className='tip-content'>
                            <img alt='pencil' src='/img/writing-tip.png' />
                            <div className='tip-text'>
                                <p>Your title should summarize the problem.</p>
                                <p>You might find that you have a better idea of your title after writing out the rest of the question.</p>
                            </div>
                        </div>
                    </FirstTipBox>
                    )}
                </FormContainer>
                <FormContainer>
                    <TextBox>
                        <label className='title'>What are the details of your problem?</label>
                        <label className='description'>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</label>
                        <TextEditor 
                            onFocus={() => setFocusedElement('problem')}
                        />
                        <NextBtn>Next</NextBtn>
                    </TextBox>
                    {focusedElement === 'problem' && (
                    <SecondTipBox>
                        <div className='tip-title'>Introduce the problem</div>
                        <div className='tip-content'>
                            <img alt='pencil' src='/img/writing-tip.png' />
                            <div className='tip-text'>
                                <p>Explain how you encountered the problem you’re trying to solve, and any difficulties that have prevented you from solving it yourself.</p>
                            </div>
                        </div>
                    </SecondTipBox>
                     )}
                </FormContainer>
                <FormContainer>
                    <TextBox>
                        <label className='title'>What did you try and what were you expecting?</label>
                        <label className='description'>Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters.</label>
                        <TextEditor onFocus={() => setFocusedElement('expect')} />
                        <NextBtn>Next</NextBtn>
                    </TextBox>
                    {focusedElement === 'expect' && (
                    <ThirdTipBox>
                        <div className='tip-title'>Expand on the problem</div>
                        <div className='tip-content'>
                            <img alt='pencil' src='/img/writing-tip.png' />
                            <div className='tip-text'>
                                <p>Show what you’ve tried, tell us what happened, and why it didn’t meet your needs.</p>
                                <p>Not all questions benefit from including code, but if your problem is better understood with code you’ve written, you should include a minimal, reproducible example.</p>
                                <p>Please make sure to post code and errors as text directly to the question (and not as images), and format them appropriately.</p>
                            </div>
                        </div>
                    </ThirdTipBox>
                     )}
                </FormContainer>
                <FormContainer>
                    <TagContentBox>
                        <div className='label-box'>
                            <label className='title'>Tags</label>
                            <label className='description'>Add up to 5 tags to describe what your question is about. Start typing to see suggestions.</label>
                        </div>
                        <input 
                            type='text' 
                            maxlength='300' 
                            placeholder='e.g. (asp.net-mvc objective-c ruby-on-rails)' 
                            onFocus={() => setFocusedElement('tags')} 
                        />
                        <NextBtn>Next</NextBtn>
                    </TagContentBox>
                    {focusedElement === 'tags' && (
                    <FourthTipBox>
                        <div className='tip-title'>Adding tags</div>
                        <div className='tip-content'>
                            <img alt='pencil' src='/img/writing-tip.png' />
                            <div className='tip-text'>
                                <p>Tags help ensure that your question will get attention from the right people.</p>
                                <p>Tag things in more than one way so people can find them more easily. Add tags for product lines, projects, teams, and the specific technologies or languages used.</p>
                                <p>Learn more about tagging</p>
                            </div>
                        </div>
                    </FourthTipBox>
                    )}
                </FormContainer>
                <PostBtn>Post your question</PostBtn>
                <DiscardBtn>Discard draft</DiscardBtn>
            </AskContainer>
        </AskPage>
    )
  }
  
export default Ask;

export  const AskPage = styled.div`
    display: flex;
    justify-content: center;
    background-color: #F8F9F9;
    height: 100%;
    width: 100%;
`;

export  const AskContainer = styled.div`
    width: 1264px;
    padding: 0 24px 24px;
    margin-bottom: 300px;
`;

export  const HeadContainer = styled.div`
    width: 100%;
    height: 130px;
    align-items: center;
    display: flex;
    justify-content: space-between;

    h1 {
        align-items: center;
        font-size: 27px;
    }

    .robot-img {
        height: 130px;
    }
`;

export  const GuideBox = styled.div`
    width: 70%;
    height: 250px;
    border: 1px solid #A6CEED;
    background-color: #EBF4FB;
    border-radius: 3px;
    padding: 24px;
    margin: 16px 0;

    h2 {
        font-weight: 400;
        font-size: 21px;
        margin: 0 0 9px;
        color: hsl(210,8%,25%);
    }

    p {
        margin: 0;
        font-size: 15px;
        margin: 1px 0;
    }

    a {
        color: #0074CC !important;
        text-decoration: none;
        cursor: pointer;
    }

    .p2 {
        margin-bottom: 15px;
    }

    h5 {
        font-size: 13px;
        margin: 17px 0 9px;
        color: hsl(210,8%,25%);
    }

    ul {
        font-size: 13px;
        margin-left: -10px;
        margin-top: 0;
    }

    li {
        padding: 1px 0;
    }
`;

export  const FormContainer = styled.div`
    display: flex;
    width: 100%;
    justify-content: space-between;
    margin-bottom: 12px;
`;

export  const ContentBox = styled.div`
    width: 70%;
    border: 1px solid hsl(210,8%,85%);
    border-radius: 3px;
    background-color: white;
    display: flex;
    flex-direction: column;
    padding: 24px;

    .label-box {
        display: flex;
        flex-direction: column;
        padding: 0 2px;
    }

    .title {
        font-size: 15px;
        font-weight: 600;
        margin-bottom: 3px;
    }

    .description {
        font-size: 12px;
        margin: 2px 0 6px;
    }

    input {
        padding: 8px 9px;
        height: 33px;
        border: 1px solid hsl(210,8%,75%);
        border-radius: 3px;
        font-size: 13px;
    }

    input:focus {
        box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
        border: 1px solid #59a4de;
        outline: none;
    }
`;

const NextBtn = styled(RegistLink)`
    width: 51px;
    padding: 10px;
    margin-top: 8px;
`

export  const FirstTipBox = styled.div`
    width: 28.5%;
    height: 165px;
    border: 1px solid hsl(210,8%,85%);
    box-shadow: 0 1px 2px hsla(0,0%,0%,0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05), 0 2px 8px hsla(0, 0%, 0%, 0.05);
    background-color: white;

    .tip-title {
        width: 100%;
        padding: 12px;
        font-size: 15px;
        background-color: #F8F9F9;
        border-bottom: 1px solid hsl(210,8%,85%);
    }

    .tip-content {
        width: 100%;
        margin: 16px;
        display: flex;
    }

    img {
        width: 38px;
        height: 44px;
        margin: 0 8px;
    }
    
    .tip-text {
        display: block;
        font-size: 12px;
        margin: 0 8px;
        width: 235px;
        word-break: normal;
    }

    p {
        margin: 0 0 12px;
    }
`;

const TextBox = styled(ContentBox)`

    .description {
        margin: 2px 0 6px;
    }
`

const SecondTipBox = styled(FirstTipBox)`
    height: 153px;

    .tip-text{
        width: 250px;
    } 
`

const ThirdTipBox = styled(FirstTipBox)`
    height: 287px;

    .tip-text{
        width: 250px;
    } 
`

const FourthTipBox = styled(FirstTipBox)`
    height: 224px;

    .tip-text{
        width: 250px;
    } 
`

const PostBtn = styled(RegistLink)`
    width: 135px;
    height: 38px;
    padding: 10px;
    margin-right: 8px;
`

export const DiscardBtn = styled.button`
  height: 38px;
  background-color: transparent;
  border: none;
  border-radius: 3px;
  box-shadow: rgba(255, 255, 255, .4) 0 1px 0 0 inset;
  box-sizing: border-box;
  color: hsl(358,62%,47%);
  cursor: pointer;
  display: inline-block;
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

  &:hover,&:focus {
    color: hsl(358,64%,41%);
    background-color: hsl(358,75%,97%);
  }

  &:focus {
  box-shadow: 0 0 0 4px rgba(171, 38, 42, .15);
  background-color: #F8D2D3;
  }

  &:active {
   background-color: hsl(358,76%,90%);
   box-shadow: none;
  }
`

const TagContentBox = styled(ContentBox)`
    height: 170.5px;
    
`;