package br.com.fiapsoat.entities.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessError {

    @Schema(description = "Descrição resumida do erro", example = "Dados inválidos")
    private String erro;

    @Schema(description = "Lista dos detalhes identificados durantes as validações", example = "O campo nome do cliente é obrigatório")
    private List<String> detalhes;

    @Schema(description = "Data e hora em que o evento ocorreu", example = "2023-01-01'T'00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

}
