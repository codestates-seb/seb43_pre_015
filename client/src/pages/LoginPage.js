import { Link } from 'react-router-dom';
import styled from 'styled-components';

function LoginPage() {
  return (
    <LoginPageBox>
      <LoginBox>
        <LoginLogo href="#">
          <LoginLogoImg src={process.env.PUBLIC_URL + '/logo-stackoverflow-sm.svg'} />
        </LoginLogo>

        <SocialLoginBtn>
          <SocialLoginLinkBox>
            <SocialLoginLogo src={process.env.PUBLIC_URL + '/logo-google.png'} />
            <SocialLoginContent>Log in with Google</SocialLoginContent>
          </SocialLoginLinkBox>
        </SocialLoginBtn>

        <UserLoginFormBox>
          <EmailFormBox>
            <EmailIndicator>Email</EmailIndicator>
            <EmailInput />
          </EmailFormBox>
          <PasswordFormBox>
            <PasswordGuideBox>
              <PasswordIndicator>Password</PasswordIndicator>
              <FindPassword href="#">Forgot Password?</FindPassword>
            </PasswordGuideBox>
            <PasswordInput />
          </PasswordFormBox>
          <LoginSubmitBox>
            <LoginSubmitBtn to="/questionlist">Log in</LoginSubmitBtn>
          </LoginSubmitBox>
        </UserLoginFormBox>

        <SignUpLinkBox>
          <SignUpText>Don't have an account?</SignUpText>
          <SignUpLink to="/signup">Sign up</SignUpLink>
        </SignUpLinkBox>
      </LoginBox>
    </LoginPageBox>
  )
}

const LoginPageBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f1f2f3;
  height: calc(100vh - 50px);
`

const LoginBox = styled.div`
  width: 278px;
  display: flex;
  flex-direction: column;
  align-items: center;
`

const LoginLogo = styled.a`
  display: block;
  width: 62px;
  height: 67px;
  margin-bottom: 20px;
`

const LoginLogoImg = styled.img`
  width: 100%;
  height: 100%;
`

const SocialLoginBtn = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 40px;
  background-color: #fff;
  border: 1px solid #d6d9dc;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 20px;

  &:hover {
    background-color: hsl(210, 8%, 97.5%);
  }
`

const SocialLoginLinkBox = styled.a`
  display: flex;
  justify-content: center;
  align-items: center;
`

const SocialLoginLogo = styled.img`
  display: block;
  width: 18px;
  height: 18px;
  margin-right: 5px;
`

const SocialLoginContent = styled.div`
  color: #232629;
  font-size: 13px;
`

const UserLoginFormBox = styled.div`
  width: 278px;
  height: 234px;
  padding: 24px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 4px 4px #d9d9d9;
  margin-bottom: 30px;
`

const EmailFormBox = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
`

const EmailIndicator = styled.div`
  font-weight: bold;
  margin-bottom: 5px;
  color: #0c0d0e;
  font-size: 15px;
`

const EmailInput = styled.input`
  width: 100%;
  height: 32px;

  &:focus {
    box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
    border: 1px solid #59a4de;
    outline: none;
  }
`

const PasswordFormBox = styled.div`
  margin-bottom: 15px;
`

const PasswordGuideBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 5px;
`

const PasswordIndicator = styled.div`
  font-weight: bold;
  color: #0c0d0e;
  font-size: 15px;
`

const FindPassword = styled.a`
  text-decoration: none;
  color: #0074cc;
  font-size: 12px;

  &:hover {
    color: #0a95ff;
  }
`

const PasswordInput = styled.input`
  width: 100%;
  height: 32px;

  &:focus {
    box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
    border: 1px solid #59a4de;
    outline: none;
  }
`

const LoginSubmitBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`

const LoginSubmitBtn = styled(Link)`
  width: 100%;
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

const SignUpLinkBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`

const SignUpText = styled.div`
  color: #232629;
  font-size: 14px;
  margin-right: 5px;
`

const SignUpLink = styled(Link)`
  color: #0074cc;
  text-decoration: none;
  font-size: 14px;

  &:hover {
    color: #0a95ff;
  }
`
export default LoginPage;