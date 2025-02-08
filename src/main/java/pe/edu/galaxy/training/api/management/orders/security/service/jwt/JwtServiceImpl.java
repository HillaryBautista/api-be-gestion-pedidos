package pe.edu.galaxy.training.api.management.orders.security.service.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
	
	private final Long EXPIRE=(long)21_600_000;// (1000 * 60 * 60 ); // Milisegundos
	
	private final String ISSUER_INFO="PROYECTO HILLARY";
	
	@Value("${token.signing.key}")
	private String jwtSigningKey;

	@Override
	public String extractUser(String token) {
		return getAllClaims(token).getSubject();
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUser(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
		return Jwts.builder().claims(claims).subject(userDetails.getUsername())
		.issuer(ISSUER_INFO).issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis() + EXPIRE))
		//.claim("idsistema", "1") // 
		.signWith(getSigningSecretKey()).compact();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return getAllClaims(token).getExpiration();
	}
	

	public Claims getAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigningSecretKey()).build().parseSignedClaims(token).getPayload();
	}

	private SecretKey getSigningSecretKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
