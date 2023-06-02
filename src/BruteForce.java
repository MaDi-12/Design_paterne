import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class BruteForce extends Hashing {

   

    public String getPassword(String hash) {
        String[] hashTypes = { "MD5", "SHA-256" }; // Types de hachage pris en charge
        String[] passwords = genererPasswords(26*26*26*26); // Liste de mots de passe prédéfinis
        
        for (String hashType : hashTypes) {
            for (String password : passwords) {
                String hashedPassword = getHash(password, hashType);
                if (hash.equals(hashedPassword)) {
                    return password;
                }
            }
        }

        return "Mot de passe non trouvé";
    }

    public static String[] genererPasswords(int count) {
        String[] passwords = new String[count];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            StringBuilder password = new StringBuilder();
            for (int j = 0; j < 5
            ; j++) {
                int index = random.nextInt(alphabet.length());
                char lettre = alphabet.charAt(index);
                password.append(lettre);
            }
            passwords[i] = password.toString();
        }

        return passwords;
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
