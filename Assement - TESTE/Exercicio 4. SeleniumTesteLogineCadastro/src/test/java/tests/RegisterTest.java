package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.RegisterPage;
import utils.WebDriverManagerUtil;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterTest {
    private WebDriver driver;
    private RegisterPage registerPage;

    // Criando um Logger para a classe
    private static final Logger logger = Logger.getLogger(LoginTest.class.getName());

    @BeforeEach
    public void setUp() {
        driver = WebDriverManagerUtil.getDriver();
        driver.get("https://automationexercise.com/login");
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void testRegisterNewUser() {
        registerPage.enterName("Eduardo A");
        registerPage.enterEmail("eduardo.abrahao123@hotmail.com");
        registerPage.submitRegistration();

        logger.info("Teste de cadastro novo usuario finalizado.");
    }

    @AfterAll
    public void tearDown() {
        WebDriverManagerUtil.quitDriver();
    }
}
