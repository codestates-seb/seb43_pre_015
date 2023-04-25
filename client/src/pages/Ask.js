import React, { useState } from 'react';
import styled from 'styled-components';
import TextEditor from '../components/TextEditor';
import { RegistLink } from '../components/Navbar';
import AskGuide from '../components/AskGuide';
import Tag from '../components/Tag';
import DiscardModal from '../components/DiscardModal';
import axios from 'axios';

function Ask() {
    // 서버 연결
    const [title, setTitle] = useState('');
    const [problemContent, setProblemContent] = useState('');
    const [expectedContent, setExpectedContent] = useState('');
    
    const handleFormSubmit = async () => {
        console.log('Submitting form with values:', {
            memberId: 1,
            title: title,
            content: `${problemContent} ${expectedContent}`,
        });
    
        try {
            const response = await axios.post('https://e6cb-58-232-110-9.ngrok-free.app/questions/ask', {
                memberId: 1,
                title: title,
                content: `${problemContent} ${expectedContent}`,
            });
            console.log('POST request successful:', response.data);
        } catch (error) {
            console.error('Error during POST request:', error);
        }
    };

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
        console.log('Title:', event.target.value);
    };

    // 컴포넌트
    const [focusedElement, setFocusedElement] = useState(null);
    const [nextClicked, setNextClicked] = useState([false, false, false, false]);
    const [modalOpen, setModalOpen] = useState(false);
    
    const handleNextClick = (index) => {
        const nextClickedCopy = [...nextClicked];
        nextClickedCopy[index] = true;
        setNextClicked(nextClickedCopy);
    };

    const openModal = () => {
        setModalOpen(true);
    };
    const closeModal = () => {
        setModalOpen(false);
    };

    return (
        <AskPage>
            <AskContainer>
                <AskGuide />
                <FormContainer>
                    <ContentBox>
                        <div className='label-box'>
                            <label className='title'>Title</label>
                            <label className='description'>Be specific and imagine you're asking a question to another person.</label>
                        </div>
                        <input 
                            type='text'
                            maxLength='300'
                            placeholder='e.g. Is there an R function for finding the index of an element in a vector?'
                            onFocus={() => setFocusedElement('title')}
                            autoFocus
                            value={title}
                            onChange={handleTitleChange}
                        />
                        {!nextClicked[0] && <NextBtn onClick={() => handleNextClick(0)}>Next</NextBtn>}
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
                    <TextBox style={{ opacity: nextClicked[0] ? 1 : 0.5, cursor: nextClicked[0] ? 'auto' : 'not-allowed' }}>
                        <label className='title'>What are the details of your problem?</label>
                        <label className='description'>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</label>
                        <TextEditor 
                            onFocus={() => setFocusedElement('problem')}
                            handleContentChange={setProblemContent}
                        />
                        {!nextClicked[1] && <NextBtn onClick={() => handleNextClick(1)} style={{ display: nextClicked[0] ? 'block' : 'none' }}>Next</NextBtn>}
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
                    <TextBox 
                        style={{ opacity: nextClicked[1] ? 1 : 0.5, 
                        cursor: nextClicked[1] ? 'auto' : 'not-allowed' }}
                    >
                        <label className='title'>What did you try and what were you expecting?</label>
                        <label className='description'>Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters.</label>
                        <TextEditor 
                            onFocus={() => setFocusedElement('expect')}
                            handleContentChange={setExpectedContent}
                        />
                        { !nextClicked[2] && 
                            <NextBtn onClick={() => handleNextClick(2)} style={{ display: nextClicked[1] ? 'block' : 'none' }}>Next</NextBtn>}
                    </TextBox>
                    {focusedElement === 'expect' && (
                    <ThirdTipBox>
                        <div className='tip-title'>Expand on the problem</div>
                        <div className='tip-content'>
                            <img alt='pencil' src='/img/writing-tip.png' />
                            <div className='tip-text'>
                                <p>Show what you’ve tried, tell us what happened, and why it didn’t meet your needs.</p>
                                <p>Not all questions benefit from including code, but if your problem is better understood with code you’ve written, you should include <a href="https://stackoverflow.com/help/minimal-reproducible-example">minimal, reproducible example</a>.</p>
                                <p>Please make sure to post code and errors as text directly to the question (and <a href="https://meta.stackoverflow.com/questions/285551">not as images</a>), and <a href="https://stackoverflow.com/help/formatting">format them appropriately</a>.</p>
                            </div>
                        </div>
                    </ThirdTipBox>
                     )}
                </FormContainer>
                <FormContainer>
                    <LastBox>
                        <TagContentBox style={{ opacity: nextClicked[2] ? 1 : 0.5, cursor: nextClicked[2] ? 'auto' : 'not-allowed' }}>
                            <div className='label-box'>
                                <label className='title'>Tags</label>
                                <label className='description'>Add up to 5 tags to describe what your question is about. Start typing to see suggestions.</label>
                            </div>
                            <Tag onFocus={() => setFocusedElement('tags')} />
                            {!nextClicked[3] && <NextBtn onClick={() => handleNextClick(3)} style={{ display: nextClicked[2] ? 'block' : 'none' }}>Next</NextBtn>}
                        </TagContentBox>
                        <ButtonBox style={{ opacity: nextClicked[3] ? 1 : 0.5, cursor: nextClicked[3] ? 'auto' : 'not-allowed', display: nextClicked[3] ? 'block' : 'none' }}>
                            <PostBtn onClick={() => handleFormSubmit()}>Post your question</PostBtn>
                            <DiscardBtn onClick={openModal}>Discard draft</DiscardBtn>
                        </ButtonBox>
                    </LastBox>
                    {focusedElement === 'tags' && (
                    <FourthTipBox>
                        <div className='tip-title'>Adding tags</div>
                        <div className='tip-content'>
                            <img alt='pencil' src='/img/writing-tip.png' />
                            <div className='tip-text'>
                                <p>Tags help ensure that your question will get attention from the right people.</p>
                                <p>Tag things in more than one way so people can find them more easily. Add tags for product lines, projects, teams, and the specific technologies or languages used.</p>
                                <p><a href="https://stackoverflow.com/help/tagging">Learn more about tagging</a></p>
                            </div>
                        </div>
                    </FourthTipBox>
                    )}
                </FormContainer>
                <DiscardModal open={modalOpen} close={closeModal} />
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
    width: 348px;
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

    a {
        color: #0074CC !important;
        text-decoration: none;
        cursor: pointer;
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
    width: 100%;

    input {
        border: none;
        background-color: transparent;
        padding: 0;
        height: auto;
        font-size: 13px;
    }

    input:focus {
        box-shadow: none;
        border: none;
        outline: none;
    }
`;

const LastBox = styled.div`
    display: flex;
    flex-direction: column;
    width: 70%;
`;

export const ButtonBox = styled.div`
    display: flex;
    margin-top: 12px;
`;
