package br.com.fiapsoat.web.controller;

import br.com.fiapsoat.controller.AuthController;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.presenters.dto.CpfDTO;
import br.com.fiapsoat.usecases.auth.AuthUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@Tag(description = "Gera e valida o token de acesso a api", name = "Autenticação api")
public class AuthControllerImpl implements AuthController {

    private final AuthUseCase authUseCase;

    @Override
    @PostMapping(path = "/gerar-token")
    @Operation(summary = "Gerar token para cpf", tags = "Autenticação api", description = "Gera um token com cpf caso o cliente seja cadastrado")
    public String generateToken(@RequestBody CpfDTO cpf) {
        return authUseCase.generateToken(cpf.getCpf());
    }

    @Override
    @GetMapping(path = "/token-expirado")
    @Operation(summary = "Verifica se o token esta válido", tags = "Autenticação api", description = "Verifica se a data de expiração do token não foi atingido")
    public Boolean validate(@RequestHeader String token) {
        return authUseCase.isTokenExpired(token);
    }

}
