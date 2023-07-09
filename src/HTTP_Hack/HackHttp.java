package HTTP_Hack;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class HackHttp {
    public static void main(String[] args) {
        // client HTTP
        HttpClient client = HttpClient.newHttpClient();

        // URL 
        String loginUrl = "http://localhost:8888/TP_PC/verification.php";

        // données d'identification
        Map<String, String> formData = new HashMap<>();
        formData.put("username", "madi");
        formData.put("password", "passer");

        //requête HTTP POST avec les données d'identification
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(loginUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(buildFormData(formData))
                .build();

        try {
            // Envoie de la  requête et recuperation de la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // le code de retour
            int statusCode = response.statusCode();

            if (statusCode == 200) {
                // Authentification réussie
                System.out.println("Authentification réussie !");
                String responseBody = response.body();
                
            } else {
                // Authentification échouée
                System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Méthode utilitaire pour construire les données du formulaire
    private static HttpRequest.BodyPublisher buildFormData(Map<String, String> data) {
        String formData = data.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((param1, param2) -> param1 + "&" + param2)
                .orElse("");
        return HttpRequest.BodyPublishers.ofString(formData);
    }
}
