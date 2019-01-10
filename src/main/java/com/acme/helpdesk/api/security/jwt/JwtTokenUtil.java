package com.acme.helpdesk.api.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_EXPIRED = "exp";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	// retorna username/email de um token
	public String getUsernameFromToken(String token) {
		String username;

		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();

		} catch (Exception e) {
			username = null;
		}

		return username;
	}

	// Retorna data de expiração do token
	public Date getExpirationDateFromToken(String token) {
		Date expiration;

		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();

		} catch (Exception e) {
			expiration = null;
		}

		return expiration;
	}

	// Gera novo token
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());

		final Date createdDate = new Date();
		claims.put(CLAIM_KEY_CREATED, createdDate);

		return doGenerateToken(claims);

	}

	// Gera novo token - metodo auxiliar
	private String doGenerateToken(Map<String, Object> claims) {
		final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
		final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

		return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret)
				.compact();

	}

	// verifica se o token pode ser atualizado
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token));
	}

	public String refreshToken(String token) {
		String refreshedToken;

		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = doGenerateToken(claims);

		} catch (Exception e) {
			refreshedToken = null;
		}

		return refreshedToken;
	}

	// Verifica se token é valido
	public Boolean validadeToken(String token, UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}

	// checa se o token está expirado
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);

		return expiration.before(new Date());
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;

		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}

		return claims;
	}

}
