package com.pre015.server.advice;

import com.pre015.server.auth.jwt.JwtTokenizer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class VerifyAdvice {
    private final JwtTokenizer jwtTokenizer;

    public VerifyAdvice(JwtTokenizer jwtTokenizer) {
        this.jwtTokenizer = jwtTokenizer;
    }

    protected String getHeader(String headerName) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        return request.getHeader(headerName);
    }

    protected long getMemberIdFromJws(String jws) {
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        return Long.parseLong(claims.get("memberId").toString());
    }
}