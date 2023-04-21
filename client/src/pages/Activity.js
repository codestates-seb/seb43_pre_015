import styled from 'styled-components';
import UserProfile from '../components/UserProfile';
import Sidebar from '../components/Sidebar';

const Activity = () => {

    return (
        <ActivityPageContainer>
            <Sidebar />
            <ActivityContainer>
                <UserProfile />
                <div className='activity-box'>
                    <span className='activity-title'>0 Answers</span>
                    <div className='item-box'><span className='empty-text'>You have not <span className='blue'>answered</span> any questions</span></div>
                </div>
                <div className='activity-box'>
                    <span className='activity-title'>0 Quesetions</span>
                    <div className='item-box'><span className='empty-text'>You have not <span className='blue'>asked</span> any questions</span></div>
                </div>
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

    .activity-box {
        margin-bottom: 16px;
        display: flex;
        flex-direction: column;
    }

    .activity-title {
        color: #232629;
        font-size: 21px;
        margin-bottom: 8px;
    }

    .item-box {
        width: 100%;
        border: 1px solid hsl(210,8%,85%);
        border-radius: 5px;
        height: 165px;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .empty-text {
        font-size: 13px;
        color: hsl(210,8%,45%);
    }

    .blue {
        color: hsl(206,100%,40%);
    }
`;