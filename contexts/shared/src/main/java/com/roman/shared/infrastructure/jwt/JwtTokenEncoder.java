package com.roman.shared.infrastructure.jwt;

import com.roman.shared.domain.TokenEncoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        String signatureString = signatureAlgorithm.toString();

        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", signatureString);
        header.put("typ", "JWT");
        header.put("_id", id);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, Integer.parseInt(env.getRequiredProperty("jwt.expiration.time")));
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(Keys.secretKeyFor(signatureAlgorithm))
                .setExpiration(calendar.getTime())
                .setHeader(header);
        return jwtBuilder.compact();
    }

    @Override
    public HashMap<String, Object> decode(String token) {
        Claims claims = Jwts.parser().parseClaimsJws(token).getBody();
        return new HashMap<>(claims);
    }
}
