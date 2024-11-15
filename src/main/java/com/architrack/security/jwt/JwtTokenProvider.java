package com.architrack.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.architrack.exception.InvalidJwtAuthenticationException;
import com.architrack.jwt.acess.TokenVo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {

	@Value("${security-token.jwt.token.secret-key:secret}")
	private String secretKey = "secret";
	@Value("${security-token.jwt.token.expired-length:3600000}")
	private long expiredLength = 3600000;
	
	@Autowired
	public UserDetailsService detailsService;
	
	Algorithm algorithm;
	
	@PostConstruct
	protected void setup() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}
	//atualizar o tokne
	
	public TokenVo refreshToken(String refreshToken) {
		
		if(refreshToken.contains("Bearer ")) {
			
			refreshToken = refreshToken.substring("Bearer ".length());
		}
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decoded = verifier.verify(refreshToken);
		String username = decoded.getSubject();
		List<String> roles = decoded.getClaim("roles").asList(String.class);
		
		return createAcessToken(username,roles);
	}
	
	public TokenVo createAcessToken(String username, List<String>roles) {
		
		Date create = new Date();
		Date expired = new Date(create.getTime() + expiredLength);
		var acessToken = acessToken(username, roles, create, expired);
		var refreshToken = refreshToken(username,roles,create);
		
		return new TokenVo(username, true, create, expired, acessToken, refreshToken);
		
	}


	private String refreshToken(String username, List<String> roles, Date create) {
		
		Date valideted = new Date(create.getTime() + (expiredLength * 3));
		
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(create)
				.withExpiresAt(valideted)
				.withSubject(username)
				.sign(algorithm).strip();
	}


	private String acessToken(String username, List<String> roles, Date create, Date expired) {
		
		String uriAssue = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(create)
				.withExpiresAt(expired)
				.withSubject(username)
				.withIssuer(uriAssue)
				.sign(algorithm).strip();
	}
	
	public DecodedJWT decodedToken(String token) {
		
		Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(alg).build();
		DecodedJWT decoded = verifier.verify(token);
		return decoded;
		
	}
	
	public Authentication getAuthentication(String token) {
		
		DecodedJWT decoded = decodedToken(token);
		UserDetails details = this.detailsService.loadUserByUsername(decoded.getSubject());
		
		return new UsernamePasswordAuthenticationToken(details,"",details.getAuthorities());
	}
	
	public String resolveBearer(HttpServletRequest http) {
		
		String bearer = http.getHeader("Authorization");
				
		if(bearer != null && bearer.startsWith("Bearer ")) {
			bearer = bearer.substring("Bearer ".length());
			return bearer;
		}
		
		return null;
	}
	
	public boolean validetedToken(String token) {
		
		DecodedJWT decoded = decodedToken(token);
		
		try {
			
			if(decoded.getExpiresAt().before(new Date())) {
				return false;
			}
			return true;
		}
		
		catch(Exception ex) {
			
			throw new InvalidJwtAuthenticationException("Expired or invalid JWT token!");
		}
	}
	
}
