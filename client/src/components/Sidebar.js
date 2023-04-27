import styled from 'styled-components';
import { Link, useLocation } from 'react-router-dom';

function Sidebar() {
  const location = useLocation();

  return (
    <SidebarContainer>
      <ol className="sidebar-main-list">
        <li
          className={`sidebar-home ${
            location.pathname === '/questionlist' ? 'selected' : ''
          }`}
        >
          <Link to='/questionlist' activeclassname="selected">Home</Link>
        </li>
        <li className="sidebar-public">PUBLIC</li>
        <ol className="sidebar-sub-list">
          <li
            className={`sidebar-questions ${
              location.pathname === '/allquestions' ? 'selected' : ''
            }`}
          >
            <Link activeclassname="selected"> {/* All Questions 페이지 완성 시 링크 연결 */}
              <span className="material-symbols-outlined">public</span>
              Questions
            </Link>
          </li>
          <li
            className={`sidebar-user ${
              location.pathname === '/activity' || location.pathname === '/profile' ? 'selected' : ''
            }`}
          >
            <Link to='/activity' activeclassname="selected">User</Link>
          </li>
        </ol>
      </ol>
    </SidebarContainer>
  )
}

export default Sidebar

export const SidebarContainer = styled.div`
  position: relative;
  height: 100v;
  // margin-top: 50px; // for the header
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
    height: 34px;

    a {
        text-decoration: none;
        color: #525060;

      &:hover {
        cursor: pointer;
        color: black;
      }
    }
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
    height: 34px;
    padding: 8px 6px 8px 8px;
    height: 34px;
    
    a {
      text-decoration: none;
      color: #525060;

      &:hover {
        cursor: pointer;
        color: black;
      }
    }
  }

  .material-symbols-outlined {
    margin: -1px 4px 0px 0px;
    font-size: 18px;
  }

  .sidebar-user {
    display: flex;
    padding: 4px 4px 4px 30px;
    height: 34px;
    align-items: center;
    
    a {
      text-decoration: none;
      color: #525060;

      &:hover {
        cursor: pointer;
        color: black;
      }
    }
  }

  .sidebar-sub-list {
    padding: 0;
  }

  .selected {
    background-color: hsl(210, 8%, 95%);
    border-right: 3px solid #f48225;
    font-weight: bold;
    a {
      color: black;
    }
  }
`
