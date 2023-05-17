package com.ssafy.enjoytrip.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    private final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);

    private final String secret;
    private final long tokenValidityInMilliseconds;
    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }


//    Authentication: 객체 스프링 시큐리티에서 사용하는 인증객체
//    springSecurity는 credential 기반 인증 사용
//    principle : 아이디
//    credential: 페스워드
//    인증에 성공하면 Principal credential,을 담은 객체인 Authentication 객체 생성
//    authentication -> securityContext -> securityContextHolder 순으로 관리

    public String createToken(Authentication authentication) {
//      인증 객체로부터 인증 정보를 불러와 ,를 포함한 문자열로 나타내는 기능
//      User_role, User Admin...
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

//        getName 과 getPrincipal 차이
//        둘다 식별자(Principal)를 반환하지만
//        객체냐 문자열이냐 차이

//        claim?
//        JWT(JSON Web Token)의 페이로드(Payload)에 저장되는 정보입니다.
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                // private 클레임
                // AUTHORITIES_KEY를 키 authorities를 밸류로 같는 클레임
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // 아이디, 비밀번호, 권한을 이용해 User객체 생성
        // 인증 주체를 나타네는데 사용됨
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException exception) {
            LOGGER.info("잘못된 jwt 서명입니다.");
        } catch (ExpiredJwtException e) {
            LOGGER.info("만료된 jwt 토큰 입니다.");
        } catch (UnsupportedJwtException e) {
            LOGGER.info("지원하지 않는 jwt 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            LOGGER.info("jwt 토큰이 잘못되었습니다");
        }
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
}
