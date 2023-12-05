package br.com.fiapsoat.presenters.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Cliente")
public class ClienteDTO {

    @Schema(description = "Identificador do cliente", example = "1")
    private Long id;
    @Schema(description = "Nome do cliente", example = "João")
    private String nome;
    @Schema(description = "E-mail de contato do cliente", example = "joao@email.com")
    private String email;
    @Schema(description = "Cpf do cliente", example = "123.456.789-10")
    private String cpf;

}
