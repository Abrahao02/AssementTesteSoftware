package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.moandjiezana.toml.Toml;

import java.io.File;
import java.time.Duration;

public class WebDriverManagerUtil {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // Carregar a configuração do arquivo TOML
            Toml toml = new Toml().read(new File("se-config.toml"));
            String browser = toml.getString("browser"); // Pega a configuração do browser no arquivo

            // Verifica se o navegador configurado é "chrome"
            if ("chrome".equalsIgnoreCase(browser)) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
            } else {
                throw new UnsupportedOperationException("Browser " + browser + " não suportado.");
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
