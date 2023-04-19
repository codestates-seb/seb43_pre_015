import styled from 'styled-components';
import { HiBars3 } from "react-icons/hi2";
import { MdSearch } from "react-icons/md";

function Navbar() {
  return (
    <NavBox>
      <ContentBox>
        <MenuBtn>
          <HiBars3 />
        </MenuBtn>

        <Logo>
          <LogoLink href="#">
            <LogoImg src={process.env.PUBLIC_URL + '/logo-stackoverflow.png'} />
          </LogoLink>
        </Logo>

        <MenuList>
          <MenuListEl>
            <MenuListElLink href="#">About</MenuListElLink>
          </MenuListEl>
          <MenuListEl>
            <MenuListElLink href="#">Products</MenuListElLink>
          </MenuListEl>
          <MenuListEl>
            <MenuListElLink href="#">For Teams</MenuListElLink>
          </MenuListEl>
        </MenuList>

        <SearchContainer>
          <SearchIcon>
            <MdSearch />
          </SearchIcon>
          <SearchInput type="text" placeholder="Search..." />
        </SearchContainer>

        <LoginBtn>
          <LoginLink href="#">Log in</LoginLink>
        </LoginBtn>

        <RegistBtn>
          <RegistLink href="#">Sign up</RegistLink>
        </RegistBtn>
      </ContentBox>
    </NavBox>
  )
}

const NavBox = styled.div`
  display: flex;
  height: 50px;
  justify-content: center;
  align-items: center;
  border-top: 3px solid #f48225;
  box-shadow: 0 10px 10px -10px #999;
  z-index: 1;
`

const ContentBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 1264px;
  height: 100%;
`

const MenuBtn = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.75rem;
  width: 48px;
  height: 47px;
  padding: 0 16px;
  cursor: pointer;
`

const Logo = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
 
  padding: 0 8px;
`

const LogoLink = styled.a``

const LogoImg = styled.img`
  width: 160px;
  height: 30px;
`

const MenuList = styled.ul`
  display: flex;
  justify-content: space-between;
  list-style: none;
  width: 223px;
  height: 33px;
  padding-left: 10px;
  padding-right: 250px;
`

const MenuListEl = styled.li`
  display: flex;
  justify-content: center;
  align-items: center;
  white-space: nowrap;
  padding: 0 12px;
  cursor: pointer;
  
  &: first-of-type {
  }

  &: nth-of-type(2) {
  }

  &:last-of-type {
    margin-right: 0;
  }

  &:hover {
    border-radius: 1000px;
    background-color: #e3e6e8;
  }
`

const MenuListElLink = styled.a`
  text-decoration: none;
  color: hsl(210, 8%, 15%);
  font-size: 14px;
  
`

const SearchContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 33px;
  border: 1px solid rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  z-index: 1;
  opacity: 1;
  padding-left: 5px;
  margin-right: 20px;
  flex-grow: 1;
  
  &:focus-within {
    box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
    border: 1px solid #59a4de;
  }
`

const SearchIcon = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  color: #838c95;
`

const SearchInput = styled.input`
  width: calc(100% - 16px);
  border: none;
  -webkit-appearance: none;
  background-color: transparent;
  
  &:focus {
    outline: none;
  }
`

const LoginBtn = styled.div`
  background-color: #e1ecf4;
  font-size: 14px;
  margin-right: 10px;
  padding: 10px;
  border: 1px solid #80abca;
  border-radius: 3px;
  width: 70px;
  height: 33px;
  display: flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
  cursor: pointer;

  &:hover, &:focus {
    background-color: #b3d3ea;
  }

  &:focus {
    box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
  }
  
`

const LoginLink = styled.a`
  color: #3e769e;
  text-decoration: none;
`

const RegistBtn = styled.div`
  background-color: #0095ff;
  border: 1px solid transparent;
  border-radius: 3px;
  box-shadow: rgba(255, 255, 255, .4) 0 1px 0 0 inset;
  box-sizing: border-box;
  color: #fff;
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

  &:hover,
  &:focus {
  background-color: #07c;
  }

  &:focus {
  box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
  }

  &:active {
  background-color: #0064bd;
  box-shadow: none;
}
`

const RegistLink = styled.a`
  color: #fff;
  text-decoration: none;
  display: flex;
  justify-content: center;
  align-items: center;
`

export default Navbar;