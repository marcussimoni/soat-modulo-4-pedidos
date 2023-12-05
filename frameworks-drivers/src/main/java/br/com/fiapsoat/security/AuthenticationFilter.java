package br.com.fiapsoat.security;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.usecases.auth.AuthUseCase;
import br.com.fiapsoat.usecases.cliente.ClienteUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@Component
@AllArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    public static final String CLIENTE_NAO_CADASTRADO = "cliente_nao_cadastrado";
    private final AuthUseCase authUseCase;
    private final ClienteUseCase clienteUseCase;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);

        if (!authUseCase.isTokenExpired(jwt)) {

            String id = authUseCase.getSubject(jwt);
            String username = CLIENTE_NAO_CADASTRADO;

            try {
                Cliente cliente = clienteUseCase.buscarClientePorId(Long.valueOf(id));
                username = cliente.getCpf().getValue();
            } catch (Exception e) {
                log.error("Cliente não cadastrado", e);
            }

            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("CLIENTE");
            User user = new User(username, "", true, true, true, true, authorities);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            context.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(context);
        }

        filterChain.doFilter(request, response);

    }

}
