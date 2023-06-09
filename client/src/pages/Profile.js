import styled from "styled-components";
import UserProfile from "../components/UserProfile";
import Sidebar from "../components/Sidebar";
import { useState, useEffect } from "react";
import axios from "axios";

const Profile = () => {
  const [isChecked, setIsChecked] = useState(false);
  const [displayName, setDisplayName] = useState("");
  const [profileData, setProfileData] = useState(null);
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  useEffect(() => {
    axios
      .get(
        "http://ec2-3-36-71-89.ap-northeast-2.compute.amazonaws.com:8080/api/members/1"
      )
      .then((response) => {
        console.log(response.data);
        setProfileData(response.data);
        setDisplayName(response.data.displayName);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const handleProfileSave = () => {
    const data = {
      memberId: profileData.memberId,
      displayName: displayName,
    };

    axios
      .patch(
        "http://ec2-3-36-71-89.ap-northeast-2.compute.amazonaws.com:8080/api/members/1",
        data
      )
      .then((response) => {
        console.log(response.data);
        setProfileData(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const handleChangePassword = () => {
    const data = {
      password: profileData.password,
      newPassword: newPassword,
    };

    axios
      .patch(
        `https://e6cb-58-232-110-9.ngrok-free.app/api/members/${profileData?.memberId}/password`,
        data
      )
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const handleDeleteProfile = () => {
    axios
      .delete(
        `https://e6cb-58-232-110-9.ngrok-free.app/api/members/${profileData?.memberId}`
      )
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <ProfilePageContainer>
      <Sidebar />
      <ProfileContainer>
        <UserProfile displayName={displayName} />
        <span className="main-title">Your Profile</span>
        <div className="sub-title">Edit your profile</div>
        <div className="divider-box">
          <label>Display name</label>
          <input
            type="text"
            value={displayName}
            onChange={(event) => setDisplayName(event.target.value)}
          />
          <button
            className="default-btn profile-btn"
            onClick={handleProfileSave}
          >
            Save profile
          </button>
        </div>
        <div className="sub-title">Change password</div>
        <div className="divider-box">
          <label>New password</label>
          <input
            type="password"
            value={newPassword}
            onChange={(event) => setNewPassword(event.target.value)}
          />
          <label>New password (again)</label>
          <input
            type="password"
            value={confirmPassword}
            onChange={(event) => setConfirmPassword(event.target.value)}
          />
          <p className="password-condition">
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </p>
          <button
            className="default-btn password-btn"
            onClick={handleChangePassword}
          >
            Change Password
          </button>
        </div>
        <div className="sub-title">Delete profile</div>
        <div className="divider-box delete-profile">
          <p>
            Before confirming that you would like your profile deleted, we'd
            like to take a moment to explain the implications of deletion:
          </p>
          <ul>
            <li>
              Deletion is irreversible, and you will have no way to regain any
              of your original content, should this deletion be carried out and
              you change your mind later on.
            </li>
            <li>
              Your questions and answers will remain on the site, but will be
              disassociated and anonymized (the author will be listed as
              "username") and will not indicate your authorship even if you
              later return to the site.
            </li>
          </ul>
          <p>
            Confirming deletion will only delete your profile on Stack Overflow
            - it will not affect any of your other profiles on the Stack
            Exchange network. If you want to delete multiple profiles, you'll
            need to visit each site separately and request deletion of those
            individual profiles.
          </p>
          <div className="consent-container">
            <div className="checkbox-box">
              <input
                className="delete-checkbox"
                type="checkbox"
                checked={isChecked}
                onChange={(event) => setIsChecked(event.target.checked)}
              ></input>
            </div>
            <label className="checkbox-label">
              I have read the information stated above and understand the
              implications of having my profile deleted. I wish to proceed with
              the deletion of my profile.
            </label>
          </div>
          <button
            className={`delete-btn ${!isChecked && "disabled-btn"}`}
            disabled={!isChecked}
            onClick={handleDeleteProfile}
          >
            Delete profile
          </button>
        </div>
      </ProfileContainer>
    </ProfilePageContainer>
  );
};

export default Profile;

export const ProfilePageContainer = styled.div`
  height: 100%;
  display: flex;
  justify-content: center;
`;

export const ProfileContainer = styled.div`
  // margin-top: 50px;
  padding: 24px;
  width: 1100px;
  display: flex;
  flex-direction: column;
  font-size: 15px;

  .main-title {
    margin: 0px 0px 24px;
    padding: 0px 0px 16px;
    border-bottom: 1px solid hsl(210, 8%, 85%);
    font-size: 27px;
    font-weight: normal;
  }

  .sub-title {
    font-size: 21px;
    margin: 0px 0px 8px;
    font-weight: normal;
  }

  .divider-box {
    display: flex;
    flex-direction: column;
    border: 1px solid hsl(210, 8%, 90%);
    border-radius: 5px;
    padding: 24px;
    margin-bottom: 48px;
  }

  label {
    padding: 6px 2px;
    font-size: 15px;
    font-weight: 600;
  }

  input {
    width: 422px;
    height: 33px;
    border: 1px solid hsl(210, 8%, 75%);
    border-radius: 3px;
    padding: 7.8px 9.1px;
    margin-bottom: 8px;
  }

  input:focus {
    box-shadow: 0 0 0 4px rgba(0, 149, 255, 0.15);
    border: 1px solid #59a4de;
    outline: none;
  }

  .default-btn {
    background-color: #0095ff;
    border: 1px solid transparent;
    border-radius: 3px;
    box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0 0 inset;
    box-sizing: border-box;
    color: #fff;
    cursor: pointer;
    display: inline-block;
    font-family: -apple-system, system-ui, "Segoe UI", "Liberation Sans",
      sans-serif;
    font-size: 13px;
    font-weight: 500;
    line-height: 1.15385;
    margin: 0;
    outline: none;
    padding: 8px 0.8em;
    position: relative;
    text-align: center;
    text-decoration: none;
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
    vertical-align: baseline;
    white-space: nowrap;
  }

  .default-btn:hover,
  .default-btn:focus {
    background-color: #07c;
  }

  .default-btn:focus {
    box-shadow: 0 0 0 4px rgba(0, 149, 255, 0.15);
  }

  .default-btn:active {
    background-color: #0064bd;
    box-shadow: none;
  }

  .delete-checkbox {
    font-size: 15px;
  }

  .profile-btn {
    width: 94px;
    height: 38px;
    margin-top: 4px;
  }

  .password-condition {
    font-size: 12px;
    margin: 4px 0px;
    color: hsl(210, 8%, 45%);
  }

  .password-btn {
    width: 422px;
    margin-top: 4px;
    height: 38px;
  }

  .consent-container {
    display: flex;
  }

  .checkbox-box {
    display: block;
    height: 45px;
    width: 15px;
    margin: 4px 4px 0 -4px;
  }

  .delete-checkbox {
    width: 100%;
    height: 15px;
  }

  .delete-checkbox:hover {
    cursor: pointer;
  }

  .checkbox-label {
    padding: 0px 2px;
    margin: 4px;
    font-weight: 400;
  }

  .delete-btn {
    background-color: hsl(358, 62%, 52%);
    border: 1px solid transparent;
    border-radius: 3px;
    box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0 0 inset;
    box-sizing: border-box;
    color: #fff;
    cursor: pointer;
    display: inline-block;
    font-family: -apple-system, system-ui, "Segoe UI", "Liberation Sans",
      sans-serif;
    font-size: 13px;
    font-weight: 500;
    line-height: 1.15385;
    outline: none;
    padding: 8px 0.8em;
    position: relative;
    text-align: center;
    text-decoration: none;
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
    vertical-align: baseline;
    white-space: nowrap;
    width: 104px;
    height: 38px;
    margin-top: 4px;
  }

  .delete-btn:hover,
  .delete-btn:focus {
    background-color: #c22e32;
  }

  .delete-btn:focus {
    box-shadow: 0 0 0 4px rgba(208, 57, 62, 0.15);
  }

  .delete-btn:active {
    background-color: #932123;
    box-shadow: none;
  }

  .disabled-btn {
    opacity: 0.5;
    cursor: not-allowed;
  }

  .delete-btn:disabled:hover {
    background-color: hsl(358, 62%, 52%);
    cursor: not-allowed;
  }
`;
