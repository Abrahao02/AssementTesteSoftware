import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCepApiTest {

    public static void main(String[] args) {
        // Exemplo de CEP para teste
        String cep = "20720310";  // Substitua por qualquer CEP para testar

        // URL da API
        String urlString = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            // Criação do objeto URL
            URL url = new URL(urlString);

            // Conexão com a URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);  // Timeout para conexão
            connection.setReadTimeout(5000);     // Timeout para leitura da resposta

            // Verifica o código de resposta
            int status = connection.getResponseCode();
            if (status != 200) {
                System.out.println("Erro: " + status);
                return;
            }

            // Lê a resposta da API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Exibe a resposta no console
            System.out.println("Resposta da API ViaCEP:");
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
