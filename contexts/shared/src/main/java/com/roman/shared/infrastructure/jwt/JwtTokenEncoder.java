package com.roman.shared.infrastructure.jwt;

import com.roman.shared.domain.TokenEncoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;

@Service
public class JwtTokenEncoder implements TokenEncoder {
    private final Environment env;

    public JwtTokenEncoder(Environment env) {
        this.env = env;
    }

    @Override
    public String encode(String id) {
        String secretToken = env.getRequiredProperty("jwt.secret.key");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String signatureString = signatureAlgorithm.toString();

        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", signatureString);
        header.put("typ", "JWT");

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("_id", id);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, Integer.parseInt(env.getRequiredProperty("jwt.expiration.time")));
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(signatureAlgorithm, secretToken.getBytes(StandardCharsets.UTF_8))
                .setExpiration(calendar.getTime())
                .setClaims(claims)
                .setHeader(header);
        return jwtBuilder.compact();
    }

    @Override
    public HashMap<String, Object> decode(String token) {
        String secretToken = env.getRequiredProperty("jwt.secret.key");
        Claims claims = Jwts.parser()
                .setSigningKey(secretToken.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        return new HashMap<>(claims);
    }
}
