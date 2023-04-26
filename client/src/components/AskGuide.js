import styled from "styled-components";

const AskGuide = () => {

    return (
        <>
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
        </>
    )
};

export default AskGuide;

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