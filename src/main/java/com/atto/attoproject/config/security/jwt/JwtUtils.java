package com.atto.attoproject.config.security.jwt;


import com.atto.attoproject.service.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtUtils {
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String BEARER_PREFIX = "Bearer ";

  // 토큰 유효시간 30분
  private long tokenValidTime = 60 * 60 * 1000L;

  @Value("${jwt.secret-key}")
  private String secretKey;
  private final UserDetailsService userDetailsService;

  @PostConstruct
  private void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  // JWT 토큰 생성
  public String createToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    Claims claims = Jwts.claims().setSubject(userPrincipal.getUserId()); // JWT payload 에 저장되는 정보단위
    claims.put("roles", authentication.getAuthorities());
    claims.put("name", userPrincipal.getUsername());

    Date now = new Date();
    return Jwts.builder()
            .setClaims(claims)  // 정보 저장
            .setIssuedAt(now)   // 토큰 발행 시간 정보
            .setExpiration(new Date(now.getTime() + tokenValidTime))  //만료 시간 설정
            .signWith(SignatureAlgorithm.HS256, secretKey)            // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
            .compact();
  }

  /**
   * 'Authorization' 헤더에서 Bearer Token을 추출하는 역할
   * @param request
   * @return
   */
  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
      return bearerToken.substring(BEARER_PREFIX.length());
    }
    return null;
  }

  /**
   * JWT 토큰이 유효한지(만료되지 않았는지) 검사하는 역할
   * @param token
   * @return
   */
  public boolean validateToken(String token) {
    try {
      Claims claims = getClaimsFormToken(token);
      return !claims.getExpiration().before(new Date());
    } catch (JwtException | NullPointerException exception) {
      log.info(exception.getMessage());
      return false;
    }
  }

  /**
   * JWT 토큰에서 Claims를 추출하는 역할
   * @param token
   * @return
   */
  private Claims getClaimsFormToken(String token) {
    return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token).getBody();
  }

  /**
   * 토큰을 이용해 사용자의 인증 정보인 Authentication 객체를 생성하고 반환하는 역할
   * @param token
   * @return
   */
  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  /**
   * JWT 토큰에서 사용자 ID를 추출하는 함수
   * @param token
   * @return
   */
  private String getUserId(String token) {
    return getClaimsFormToken(token).getSubject();
  }
}
