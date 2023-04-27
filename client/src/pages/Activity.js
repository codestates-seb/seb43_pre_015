import styled from 'styled-components';
import UserProfile from '../components/UserProfile';
import Sidebar from '../components/Sidebar';
import MyAnswers from '../components/MyAnswers';

const Activity = () => {

    return (
        <ActivityPageContainer>
            <Sidebar />
            <ActivityContainer>
                <UserProfile />
                <AnswerBox>
                    <Title>0 Answers</Title>
                    <ItemBox>
                        <MyAnswers />
                        <span className='empty-text'>You have not <span className='blue'>answered</span> any questions</span>
                    </ItemBox>
                </AnswerBox>
                <AnswerBox>
                    <Title>0 Quesetions</Title>
                    <ItemBox>
                    <div className='item-box'><span className='empty-text'>You have not <span className='blue'>asked</span> any questions</span></div>
                    </ItemBox>
                </AnswerBox>
            </ActivityContainer>
        </ActivityPageContainer>
    )
}

export default Activity;

export const ActivityPageContainer = styled.div`
    display: flex;
    justify-content: center;
    height: 100vh;
`;

export const ActivityContainer = styled.div`
    // margin-top: 50px; // for the header
    padding: 24px;
    width: 1100px;
    display: flex;
    flex-direction: column;
`;

export const AnswerBox = styled.div`
    margin-bottom: 16px;
    display: flex;
    flex-direction: column;
`;

export const Title = styled.div`
    color: #232629;
    font-size: 21px;
    margin-bottom: 8px;
`;

export const ItemBox = styled.div`
    width: 100%;
    border: 1px solid hsl(210,8%,85%);
    border-radius: 5px;
    height: 165px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    .empty-text {
        font-size: 13px;
        color: hsl(210,8%,45%);
    }

    .blue {
        color: hsl(206,100%,40%);
    }
`;