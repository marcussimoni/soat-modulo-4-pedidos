package br.com.fiapsoat.usecases.auth;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.usecases.cliente.ClienteUseCase;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
//@AllArgsConstructor
public class AuthUseCaseImpl implements AuthUseCase {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Autowired
    private ClienteUseCase clienteUseCase;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    @Override
    public String getSubject(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    @Override
    public String generateToken(String cpf) {

        String subject = "";

        try {

            Cliente cliente = clienteUseCase.buscarClientePorCpf(cpf);
            subject = cliente.getId().toString();

        } catch (Exception e) {

            log.info("Cliente não cadastrado na base de dados.");

        }

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();

    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}
