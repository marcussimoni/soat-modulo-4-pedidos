package br.com.fiapsoat.bdd.steps;

import br.com.fiapsoat.controller.ProdutoController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ListarProdutosTest {

    @Autowired
    private ProdutoController controller;

    @Given("Cliente deseja visualizar os produtos disponíveis")
    public void listar_produtos() {

    }

    @When("Cliente acessa a lista de produtos")
    public void cliente_acessa_os_produtos_disponíveis() {
    }

    @Then("Apresentar todos os produtos cadastrados e disponíveis")
    public void apresentar_todos_os_produtos_cadastrados_e_disponíveis() {
    }

}
