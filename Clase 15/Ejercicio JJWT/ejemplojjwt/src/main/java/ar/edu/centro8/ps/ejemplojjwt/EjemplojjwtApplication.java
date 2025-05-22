package ar.edu.centro8.ps.ejemplojjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EjemplojjwtApplication {

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(EjemplojjwtApplication.class, args);
		
		//construimos extra claims
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", "prueba jwt");

        //construimos jwt
        Date issuedAt = new Date (System.currentTimeMillis());
        Date expiration = new Date (issuedAt.getTime() + (1 * 10 * 1000));

        String jwt;
        jwt = Jwts.builder()

                //cabecera
                .header()
                .type("JWT")
                .and()

                //payload
                .subject("pruebajwt")
                .expiration(expiration)
                .issuedAt(issuedAt)
                .claims(extraClaims)

                //firma
                .signWith(generateKey(), Jwts.SIG.HS256)

                .compact();

		// Esperamos 10 segundos para asegurarnos que el token expira
		System.out.println("Generando token que expirará en 10 segundos...");

        //vemos nuestro jwt generado por pantalla
        System.out.println("------------------------------------------");
		System.out.println("Clave jwt generada: " + jwt);

		
		Thread.sleep(11000); // 11 segundos
		
		// Probamos el token expirado
		System.out.println("\nProbando token expirado:");
		probarExpiracion(jwt);
				 
		
			
            }
        
            private static void probarExpiracion(String jwt) {
                try {
                    // Intentamos parsear el token
                    Claims claims = Jwts.parser()
                        .setSigningKey(generateKey())
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();
                        
                    System.out.println("Token válido:");
                    System.out.println("Fecha de emisión: " + claims.getIssuedAt());
                    System.out.println("Fecha de expiración: " + claims.getExpiration());
                    
                } catch (ExpiredJwtException e) {
                    System.out.println("¡El token ha expirado!");
                    System.out.println("Mensaje de error: " + e.getMessage());
                } catch (JwtException e) {
                    System.out.println("Error al validar el token:");
                    System.out.println(e.getMessage());
                }
            }
            
            public static SecretKey generateKey () {
        //tiene que ser larga porque dijimos que cumple con HS256
        String secretKey = "esta es mi key super segura 12345688789 HOLA que tal";

        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    
    //variantes de generateKey
    // private static SecretKey generateKey() {
    //     return Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    // }

    // private static SecretKey generateKey() {
    //     byte[] keyBytes = new byte[64];
    //     return Keys.hmacShaKeyFor(keyBytes);
    // }
}




