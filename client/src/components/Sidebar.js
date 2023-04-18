import styled from 'styled-components';
import { useState } from 'react';

function Sidebar() {
  const [selectedItem, setSelectedItem] = useState(null);

  const handleItemClick = (item) => {
    setSelectedItem(item);
  }

  return (
    <SidebarContainer>
      <ol className="sidebar-main-list">
        <li
          className={`sidebar-home ${
            selectedItem === 'home' ? 'selected' : ''
          }`}
          onClick={() => handleItemClick('home')}
        >
          Home
        </li>
        <li className="sidebar-public">PUBLIC</li>
        <ol className="sidebar-sub-list">
          <li
            className={`sidebar-questions ${
              selectedItem === 'questions' ? 'selected' : ''
            }`}
            onClick={() => handleItemClick('questions')}
          >
            <span className="material-symbols-outlined">public</span>
            Questions
          </li>
          <li
            className={`sidebar-user ${
              selectedItem === 'user' ? 'selected' : ''
            }`}
            onClick={() => handleItemClick('user')}
          >
            User
          </li>
        </ol>
      </ol>
    </SidebarContainer>
  )
}

export default Sidebar

export const SidebarContainer = styled.div`
  position: relative;
  height: 100vh;
  margin-top: 50px; /* for the header */
  width: 164px;
  font-size: 13px;
  border-right: 1px solid hsl(210, 8%, 85%);

  ol {
    list-style: none;
    color: #525060;
  }

  .sidebar-home {
    display: flex;
    align-items: center;
    padding: 4px 4px 4px 8px;
    height: 26px;
  }

  .sidebar-home:hover {
    cursor: pointer;
    color: hsl(210, 8%, 5%);
  }

  .sidebar-main-list {
    width: 100%;
    margin: 0px 0px 8px;
    padding: 24px 0px 0px;
    display: flex;
    flex-direction: column;
  }

  .sidebar-public {
    font-size: 11px;
    margin: 16px 0px 4px 8px;
    padding: 0;
    color: hsl(210, 8%, 45%);
  }

  .sidebar-questions {
    display: flex;
    align-items: center;
    padding: 8px 6px 8px 8px;
  }

  .sidebar-questions:hover {
    cursor: pointer;
    color: hsl(210, 8%, 5%);
  }

  .material-symbols-outlined {
    margin: -1px 4px 0px 0px;
    font-size: 18px;
  }

  .sidebar-user {
    display: flex;
    padding: 4px 4px 4px 30px;
    height: 26px;
    align-items: center;
  }

  .sidebar-user:hover {
    cursor: pointer;
    color: hsl(210, 8%, 5%);
  }

  .sidebar-sub-list {
    padding: 0;
  }

  .selected {
    background-color: hsl(210, 8%, 95%);
    border-right: 3px solid #f48225;
    color: hsl(210, 8%, 5%);
    font-weight: bold;
  }
`
