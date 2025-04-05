import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCepApiTestInvalidos {

    @Test
    public void testCepInvalidoComLetras() {
        String cep = "ABC12345";  // CEP inválido com letras
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            assertEquals(400, status, "Deve retornar erro 400 para CEP com letras.");
        } catch (Exception e) {
            fail("Erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void testCepVazio() {
        String cep = "";  // CEP vazio
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            assertEquals(400, status, "Deve retornar erro 400 para CEP vazio.");
        } catch (Exception e) {
            fail("Erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void testCepComCaracteresEspeciais() {
        String cep = "1234-567";  // CEP com caractere especial
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            assertEquals(400, status, "Deve retornar erro 400 para CEP com caracteres especiais.");
        } catch (Exception e) {
            fail("Erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void testCepComNumeroInvalido() {
        String cep = "1234567";  // CEP com 7 caracteres
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            assertEquals(400, status, "Deve retornar erro 400 para CEP com número inválido.");
        } catch (Exception e) {
            fail("Erro inesperado: " + e.getMessage());
        }
    }
}
