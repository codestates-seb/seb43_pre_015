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
            <LoginSubmitBtn>Log in</LoginSubmitBtn>
          </LoginSubmitBox>
        </UserLoginFormBox>

        <SignUpLinkBox>
          <SignUpText>Don't have an account?</SignUpText>
          <SignUpLink href="#">Sign up</SignUpLink>
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
  height: 50px;
  background-color: #fff;
  border: 1px solid #d6d9dc;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 20px;
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
  font-size: 14px;
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
`

const EmailInput = styled.input`
  width: 100%;
  height: 32px;
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
`

const FindPassword = styled.a`
  text-decoration: none;
  color: #0a95ff;
  font-size: 12px;
`

const PasswordInput = styled.input`
  width: 100%;
  height: 32px;
`

const LoginSubmitBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`

const LoginSubmitBtn = styled.button`
  width: 100%;
  background-color: #0995ff;
  color: #fff;
  border: none;
  cursor: pointer;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #279afe;
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

const SignUpLink = styled.a`
  color: #0a95ff;
  text-decoration: none;
  font-size: 14px;
`
export default LoginPage;