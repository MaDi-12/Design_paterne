import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class BruteForce extends Hashing {

   

    public String getPassword(String hash) {
        String[] hashTypes = { "MD5", "SHA-256" }; // Types de hachage pris en charge
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();

        for (String hashType : hashTypes) {
            int passwordLength = 4; // Longueur initiale du mot de passe
            StringBuilder password = new StringBuilder();

            while (true) {
                for (int i = 0; i < passwordLength; i++) {
                    int index = random.nextInt(alphabet.length());
                    char letter = alphabet.charAt(index);
                    password.append(letter);
                }

                // Calcule le hachage du mot de passe généré
                String hashedPassword = getHash(password.toString(), hashType);

                // Vérification du hash
                if (hash.equals(hashedPassword)) {
                    return password.toString();
                }

                password.setLength(0);

                // Augmentation de la longueur du mot de passe si aucune correspondance n'est trouvée
                passwordLength++;
            }
        }

        return "Mot de passe non trouvé";
    }

    

    public static String getHash(String password,String type) {
        try {
            
            // Créer une instance de MessageDigest avec l'algorithme correspondant
            MessageDigest md = MessageDigest.getInstance(type);

            // Calculer le hachage MD5
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            System.out.println(sb.toString());
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
}
