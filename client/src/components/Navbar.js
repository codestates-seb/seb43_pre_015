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
  justify-content: center;
  align-items: center;
  width: 90%;
  height: 100%;
`

const MenuBtn = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 1.75rem;
  margin-right: 20px;
`

const Logo = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`

const LogoLink = styled.a``

const LogoImg = styled.img`
  width: 160px;
  height: 30px;
`

const MenuList = styled.ul`
  display: flex;
  list-style: none;
  padding: 0;
  margin-right: 20px;
`

const MenuListEl = styled.li`
  margin-right: 20px;
  
  display: flex;
  justify-content: center;
  align-items: center;

  &:first-of-type {
    margin-left: 20px;
  }

  &:last-of-type {
    margin-right: 0;
  }
`

const MenuListElLink = styled.a`
  text-decoration: none;
  color: #6b7277;
  font-size: 14px;
  
`

const SearchContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40%;
  height: 30px;
  border: 1px solid rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  z-index: 1;
  opacity: 1;
  padding-left: 5px;
  margin-right: 20px;
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
  border-radius: 5px;
  width: 70px;
  height: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
`

const LoginLink = styled.a`
  color: #3e769e;
  text-decoration: none;
`

const RegistBtn = styled.div`
  background-color: #0995ff;
  font-size: 14px;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #279afe;
  width: 80px;
  height: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
`

const RegistLink = styled.a`
  color: #fff;
  text-decoration: none;
  display: flex;
  justify-content: center;
  align-items: center;
`

export default Navbar;