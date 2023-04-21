import styled from 'styled-components';
import TextEditor from '../components/TextEditor';
import { RegistBtn } from '../components/Navbar';

function Ask() {
  
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
                            <label className='title'>Title</label>
                            <label className='description'>Be specific and imagine you're asking a question to another person.</label>
                            <input type='text' maxlength='300' placeholder='e.g. Is there an R function for finding the index of an element in a vector?' />
                            <NextBtn>Next</NextBtn>
                        </ContentBox>
                        <TipBox>
                            <div className='tip-title'>Writing a good title</div>
                            <div className='tip-content'>
                                <img alt='pencil' src='/img/writing-tip.png' />
                                <div className='tip-text'>
                                    <p>Your title should summarize the problem.</p>
                                    <p>You might find that you have a better idea of your title after writing out the rest of the question.</p>
                                </div>
                            </div>
                        </TipBox>
                    </FormContainer>
                    <FormContainer>
                        <ContentBox>
                            <label className='title'>What are the details of your problem?</label>
                            <label className='description'>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</label>
                            <TextEditor />
                            <NextBtn>Next</NextBtn>
                        </ContentBox>
                        <TipBox>
                            <div className='tip-title'>Introduce the problem</div>
                            <div className='tip-content'>
                                <img alt='pencil' src='/img/writing-tip.png' />
                                <div className='tip-text'>
                                    <p>Explain how you encountered the problem you’re trying to solve, and any difficulties that have prevented you from solving it yourself.</p>
                                </div>
                            </div>
                        </TipBox>
                    </FormContainer>
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
`;

export  const AskContainer = styled.div`
    width: 1264px;
    height: 100%;
    padding: 0 24px;
    display: flex;
    justify-content: center;
    flex-direction: column;
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
    border: 1px solid #A6CEED;
    background-color: #EBF4FB;
    border-radius: 3px;
    padding: 24px;
    margin: 16px 0;

    h2 {
        font-weight: 400;
        font-size: 21px;
        margin: 0 0 8px;
    }

    p {
        margin: 0;
        font-size: 15px;
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
        margin: 0 0 8px;
    }

    ul {
        font-size: 13px;
        margin: 0;
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
    height: 170px;
    border: 1px solid hsl(210,8%,85%);
    border-radius: 3px;
    background-color: white;
    display: flex;
    flex-direction: column;
    padding: 24px;

    .title {
        font-size: 15px;
        font-weight: 600;
    }

    .description {
        font-size: 12px;
        margin: 4px 0;
    }

    input {
        margin: 5px 0;
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

const NextBtn = styled(RegistBtn)`
    width: 51px;
    padding: 10px;
    margin-top: 3px;
`

export  const TipBox = styled.div`
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
        margin: 16px;
        display: flex;
        justify-content: center;
    }

    img {
        width: 38px;
        height: 44px;
        margin: 0 8px;
    }
    
    .tip-text {
        font-size: 12px;
        width: 75%;
        margin: 0 8px;
    }

    p {
        margin: 0 0 12px;
    }
`;
