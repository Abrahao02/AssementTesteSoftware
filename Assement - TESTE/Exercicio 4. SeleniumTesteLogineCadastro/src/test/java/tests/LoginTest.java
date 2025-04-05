package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.WebDriverManagerUtil;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Define a ordem dos testes
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    // Criando um Logger para a classe
    private static final Logger logger = Logger.getLogger(LoginTest.class.getName());

    @BeforeEach
    public void setUp() {
        logger.info("Iniciando o driver e acessando a página de login.");
        driver = WebDriverManagerUtil.getDriver();
        driver.get("https://automationexercise.com/login");
        loginPage = new LoginPage(driver);
        logger.info("Página de login carregada.");
    }

    @Test
    @Order(1) // Executa este teste primeiro
    public void testLoginWithInvalidCredentials() {
        logger.info("Iniciando teste de login com credenciais inválidas.");
        loginPage.enterEmail("email@invalido.com");
        loginPage.enterPassword("123456");
        loginPage.submitLogin();

        String errorMsg = loginPage.getErrorMessage();
        logger.info("Mensagem de erro recebida: " + errorMsg);
        logger.info("Teste de login com credenciais inválidas finalizado.");
    }

    @Test
    @Order(2) // Executa este teste depois
    public void testLoginWithValidCredentials() {
        logger.info("Iniciando teste de login com credenciais válidas.");
        loginPage.enterEmail("eduardo.abrahao@hotmail.com");
        loginPage.enterPassword("senha123");
        loginPage.submitLogin();

        logger.info("Teste de login com credenciais válidas finalizado.");
    }

    @AfterAll
    public void tearDown() {
        logger.info("Finalizando o teste e fechando o driver.");
        WebDriverManagerUtil.quitDriver();
    }
}
