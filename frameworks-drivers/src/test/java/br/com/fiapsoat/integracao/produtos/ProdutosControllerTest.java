package br.com.fiapsoat.integracao.produtos;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.presenters.dto.ProdutoDTO;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProdutosControllerTest {

    public static final String CPF = "111.111.111-11";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthUseCase auth;

    private final String PRODUTO_URL = "/produto";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String token;

    @BeforeEach
    public void init(){
        token = "Bearer " + auth.generateToken(CPF);
    }

    @Test
    @DisplayName("Lista todos os produtos disponíveis")
    public void deveListarOsProdutosDisponíveis() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get(PRODUTO_URL)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    @DisplayName("Deve cadastrar novos produtos")
    public void deveCadastrarNovoProduto() throws Exception {

        String payload = objectMapper.writeValueAsString(produtoDtoBuilder());

        RequestBuilder request = MockMvcRequestBuilders
                .post(PRODUTO_URL)
                .content(payload)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

    }

    @Test
    @DisplayName("Deve atualizar um produto existente")
    public void deveAtualizarUmProdutoExistente() throws Exception {

        String payload = objectMapper.writeValueAsString(produtoDtoBuilder());
        ProdutoDTO produtoDTO = salvarProduto(payload);

        RequestBuilder request = MockMvcRequestBuilders
                .put(PRODUTO_URL + "/{id}", produtoDTO.getId())
                .content(payload)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value(produtoDTO.getNome()))
                .andReturn();

    }

    @Test
    @DisplayName("Deve excluir um produto existente")
    public void deveExcluirUmProdutoExistente() throws Exception {

        String payload = objectMapper.writeValueAsString(produtoDtoBuilder());
        ProdutoDTO produtoDTO = salvarProduto(payload);

        RequestBuilder request = MockMvcRequestBuilders
                .delete(PRODUTO_URL + "/{id}", produtoDTO.getId())
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isNoContent());

    }

    private ProdutoDTO salvarProduto(String payload) throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .post(PRODUTO_URL)
                .content(payload)
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

        return objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), ProdutoDTO.class);
    }

    private ProdutoDTO produtoDtoBuilder() {
        return ProdutoDTO
                .builder()
                .preco(10.0)
                .categoria(Categoria.BEBIDA)
                .nome("Suco")
                .descricao("Suco de laranja")
                .imagem("imagem")
                .build();
    }

}
