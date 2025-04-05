import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCepApiTestEndereco {

    @Test
    public void testConsultaEnderecoValido() {
        String uf = "SP";
        String cidade = "Sao%20Paulo";
        String logradouro = "Avenida%20Paulista";
        String urlString = "https://viacep.com.br/ws/" + uf + "/" + cidade + "/" + logradouro + "/json/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            assertEquals(200, status, "Deve retornar resposta 200 para consulta de endereço válido.");

            // Lê a resposta da API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Para consulta válida, esperamos que a resposta não seja um array vazio ("[]")
            String respStr = response.toString().trim();
            assertNotEquals("[]", respStr, "A resposta deve conter resultados para endereço válido.");

        } catch (Exception e) {
            fail("Erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void testConsultaEnderecoUFInvalido() {
        String uf = "ZZ";  // UF inválida
        String cidade = "Sao%20Paulo";
        String logradouro = "Avenida%20Paulista";
        String urlString = "https://viacep.com.br/ws/" + uf + "/" + cidade + "/" + logradouro + "/json/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            // A API retorna status 200 mesmo para consulta inválida
            assertEquals(200, status, "Deve retornar resposta 200 para UF inválida.");

            // Lê a resposta da API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Para UF inválida, esperamos que a resposta seja um array vazio ("[]")
            String respStr = response.toString().trim();
            assertEquals("[]", respStr, "A resposta deve ser um array vazio para UF inválida.");

        } catch (Exception e) {
            fail("Erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void testConsultaEnderecoCidadeInvalida() {
        String uf = "SP";
        String cidade = "CidadeDesconhecida";  // Cidade inválida
        String logradouro = "Avenida%20Paulista";
        String urlString = "https://viacep.com.br/ws/" + uf + "/" + cidade + "/" + logradouro + "/json/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            assertEquals(200, status, "Deve retornar resposta 200 para cidade inválida.");

            // Lê a resposta
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Para cidade inválida, esperamos um array vazio
            String respStr = response.toString().trim();
            assertEquals("[]", respStr, "A resposta deve ser um array vazio para cidade inválida.");

        } catch (Exception e) {
            fail("Erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void testConsultaEnderecoLogradouroInexistente() {
        String uf = "SP";
        String cidade = "Sao%20Paulo";
        String logradouro = "Rua%20Inexistente";  // Logradouro inexistente
        String urlString = "https://viacep.com.br/ws/" + uf + "/" + cidade + "/" + logradouro + "/json/";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            assertEquals(200, status, "Deve retornar resposta 200 para logradouro inexistente.");

            // Lê a resposta
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Para logradouro inexistente, esperamos um array vazio
            String respStr = response.toString().trim();
            assertEquals("[]", respStr, "A resposta deve ser um array vazio para logradouro inexistente.");

        } catch (Exception e) {
            fail("Erro inesperado: " + e.getMessage());
        }
    }
}
