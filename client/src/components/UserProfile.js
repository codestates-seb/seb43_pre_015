import styled from 'styled-components';
import { useState } from 'react';

function UserProfile({ defaultSelectedMenu }) {
  const [selectedMenu, setSelectedMenu] = useState(defaultSelectedMenu || 'activity');

  const handleMenuClick = (menu) => {
    setSelectedMenu(menu);
  }

  return (
    <UserProfileContainer>
      <UserInfoContainer>
        <img className="profile-photo" alt="profile" src="/img/profile.png" />
        <div className="user-info-container">
          <div className="user-name">Nyang</div>
          <div className="user-join-container">
            <span className="material-icons">cake</span>
            <span className="user-join-text">Member for 4 months</span>
            <span className="material-icons">schedule</span>
            <span className="user-join-text">Last seen this week</span>
            <span className="material-icons">calendar_month</span>
            <span className="user-join-text">
              Visited 8 days, 1 consecutive
            </span>
          </div>
        </div>
      </UserInfoContainer>
      <NavigationContainer>
        <div
          className={`nav ${selectedMenu === 'profile' ? 'selected' : ''}`}
          onClick={() => handleMenuClick('profile')}
        >
          Profile
        </div>
        <div
          className={`nav ${selectedMenu === 'activity' ? 'selected' : ''}`}
          onClick={() => handleMenuClick('activity')}
        >
          Activity
        </div>
        <div
          className={`nav ${selectedMenu === 'settings' ? 'selected' : ''}`}
          onClick={() => handleMenuClick('settings')}
        >
          Settings
        </div>
      </NavigationContainer>
    </UserProfileContainer>
  )
}

export default UserProfile

export const UserProfileContainer = styled.div`
  display: flex;
  flex-direction: column;
`

export const UserInfoContainer = styled.div`
  display: flex;
  align-items: center;
  height: 144px;
  width: 100%;

  .profile-photo {
    width: 128px;
    height: 128px;
    margin-right: 8px;
  }

  .user-info-container {
    display: flex;
    flex-direction: column;
    margin: 8px;
  }

  .user-name {
    font-size: 34px;
    margin-bottom: 12px;
  }

  .user-join-container {
    font-size: 13px;
    display: flex;
    align-items: center;
    color: hsl(210, 8%, 45%);
  }

  .material-icons {
    font-size: 18px;
  }

  .user-join-text {
    margin: 0px 8px 0px 2px;
  }
`

export const NavigationContainer = styled.div`
  margin: 8px 0px 16px 0px;
  display: flex;

  .nav {
    padding: 6px 12px;
    border-radius: 1000px;
    color: hsl(210, 8%, 35%);
    font-size: 13px;
    margin-right: 5px;
  }

  .nav:hover {
    background-color: #e3e6e8;
    cursor: pointer;
  }

  .selected {
    background-color: #f48225;
    color: white;
  }

  .selected:hover {
    background-color: #da690c;
    color: white;
  }
`
