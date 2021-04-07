package com.roman.user.shared.infrastructure;

import com.roman.user.shared.domain.TokenEncoder;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class JwtTokenEncoder implements TokenEncoder {
    @Override
    public String encode(String username) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        String signatureString = signatureAlgorithm.toString();

        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", signatureString);
        header.put("username", username);
        header.put("typ", "JWT");

        JwtBuilder jwtBuilder = Jwts.builder().setHeader(header);
        return jwtBuilder.compact();
    }
}
