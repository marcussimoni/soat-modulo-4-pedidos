package br.com.fiapsoat.entities.cliente;

import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.entities.valueobjects.email.Email;
import br.com.fiapsoat.entities.valueobjects.nome.Nome;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@Builder
public class Cliente implements Serializable {

    private Long id;

    private Nome nome;

    private Cpf cpf;

    private Email email;

}
