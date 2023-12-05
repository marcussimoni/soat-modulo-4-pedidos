package br.com.fiapsoat.db.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.entities.valueobjects.email.Email;
import br.com.fiapsoat.entities.valueobjects.nome.Nome;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cliente")
public class TbCliente implements Serializable {

    public TbCliente(Cliente c){
        this.id = c.getId();
        this.nome = c.getNome().getValue();
        this.cpf = c.getCpf().getValue();
        this.email = c.getEmail().getValue();
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    public Cliente clienteBuilder() {
        return Cliente
                .builder()
                .id(getId())
                .cpf(new Cpf(getCpf()))
                .email(new Email(getEmail()))
                .nome(new Nome(getNome()))
                .build();
    }

}
