package br.com.fiapsoat.integracao.clientes;

import br.com.fiapsoat.presenters.dto.ClienteDTO;
import br.com.fiapsoat.usecases.auth.AuthUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClienteControllerTest {

    public static final String CLIENTE_CADASTRADO = "111.111.111-11";
    public static final String NOVO_CLIENTE = "999.999.999-99";
    public static final String EMAIL_VALIDO = "teste@teste.com";
    public static final String EMAIL_INVALIDO = "teste.com";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthUseCase auth;

    private final String CLIENTE_URL = "/cliente";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token;

    @BeforeEach
    public void init(){
        token = "Bearer " + auth.generateToken(CLIENTE_CADASTRADO);
    }

    @Test
    @DisplayName("Lista todos os clientes cadastrados")
    public void deveListarTodosOsClientesCadastrados() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get(CLIENTE_URL)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    @DisplayName("Deve cadastrar um novo cliente")
    public void deveCadastrarNovoCliente() throws Exception {

        String payload = objectMapper.writeValueAsString(clienteDtoBuilder(NOVO_CLIENTE, EMAIL_VALIDO));

        RequestBuilder request = MockMvcRequestBuilders
                .post(CLIENTE_URL)
                .content(payload)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    @DisplayName("Não deve cadastrar um cliente com o mesmo cpf")
    public void naoDeveCadastrarUmClienteComOMesmoCpf() throws Exception {

        String payload = objectMapper.writeValueAsString(clienteDtoBuilder(CLIENTE_CADASTRADO, EMAIL_VALIDO));

        RequestBuilder request = MockMvcRequestBuilders
                .post(CLIENTE_URL)
                .content(payload)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    @DisplayName("Não deve cadastrar cliente com e-mail inválido")
    public void naoDeveCadastrarUmClienteComEmailInvalido() throws Exception {

        String payload = objectMapper.writeValueAsString(clienteDtoBuilder(NOVO_CLIENTE, EMAIL_INVALIDO));

        RequestBuilder request = MockMvcRequestBuilders
                .post(CLIENTE_URL)
                .content(payload)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isBadRequest());

        payload = objectMapper.writeValueAsString(clienteDtoBuilder(NOVO_CLIENTE, ""));

        request = MockMvcRequestBuilders
                .post(CLIENTE_URL)
                .content(payload)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Não deve cadastrar cliente com nome inválido")
    public void naoDeveCadastrarUmClienteComNomeInvalido() throws Exception {

        String payload = objectMapper.writeValueAsString(clienteDtoBuilder("", NOVO_CLIENTE, EMAIL_INVALIDO));

        RequestBuilder request = MockMvcRequestBuilders
                .post(CLIENTE_URL)
                .content(payload)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Não deve cadastrar cliente com cpf inválido")
    public void naoDeveCadastrarUmClienteComCpfInvalido() throws Exception {

        String payload = objectMapper.writeValueAsString(clienteDtoBuilder("123.456.789-AB", EMAIL_INVALIDO));

        RequestBuilder request = MockMvcRequestBuilders
                .post(CLIENTE_URL)
                .content(payload)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isBadRequest());

    }

    private ClienteDTO clienteDtoBuilder(String cpf, String email) {
        return clienteDtoBuilder("teste cliente", cpf, email);
    }

    private ClienteDTO clienteDtoBuilder(String nome, String cpf, String email) {
        return ClienteDTO
                .builder()
                .cpf(cpf)
                .email(email)
                .nome(nome)
                .build();
    }

}
