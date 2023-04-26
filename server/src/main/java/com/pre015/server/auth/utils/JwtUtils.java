package com.pre015.server.auth.utils;

import com.pre015.server.auth.jwt.JwtTokenizer;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class JwtUtils {

    private final JwtTokenizer jwtTokenizer;


    public JwtUtils(JwtTokenizer jwtTokenizer) {
        this.jwtTokenizer = jwtTokenizer;
    }

    public Map<String, Object> getJwsClaimsFromRequest(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        return claims;
    }
}