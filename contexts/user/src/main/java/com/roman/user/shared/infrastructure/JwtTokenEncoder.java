package com.roman.user.shared.infrastructure;

import com.roman.user.shared.domain.TokenEncoder;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    public String encode(String username) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        String signatureString = signatureAlgorithm.toString();

        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", signatureString);
        header.put("username", username);
        header.put("typ", "JWT");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, Integer.parseInt(env.getRequiredProperty("jwt.expiration.time")));
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(env.getRequiredProperty("jwt.secret.key"))
                .setExpiration(calendar.getTime())
                .setHeader(header);
        return jwtBuilder.compact();
    }
}
