import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class main1 {
    static String mot_de_passe;

    public static void main(String[] args) {
        System.out.print("Veuillez saisir votre mot de passe : ");
        Scanner scanner = new Scanner(System.in);
        mot_de_passe = scanner.nextLine();

        System.out.println("Choisissez une option pour cracker un mot de passe :");
        System.out.println("1. Cracker un mot de passe en utilisant Brute Force");
        System.out.println("2. Cracker un mot de passe en utilisant Dictionnaire Attack");

        int option = scanner.nextInt();

        PasswordCracker passwordCracker;

        if (option == 1) {
            passwordCracker = new BruteForceCracker();
        } else if (option == 2) {
            passwordCracker = new DictionaryCracker();
        } else {
            System.out.println("Option invalide !");
            return;
        }

        passwordCracker.crackPassword(mot_de_passe);
    }
}

abstract class PasswordCracker {
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
}

class BruteForceCracker extends PasswordCracker {
    private boolean passwordFound = false;

    @Override
    protected boolean attemptCrack(String password) {
        generatePasswords("", password.length(), password);
        return passwordFound;
    }

    private void generatePasswords(String prefix, int length, String password) {
        if (passwordFound) {
            return;
        }

        if (length == 0) {
            System.out.println(prefix);
            if (prefix.equals(password)) {
                System.out.println("Le mot de passe est " + prefix);
                passwordFound = true;
            }
            return;
        }

        for (char c = 'a'; c <= 'z'; c++) {
            String newPrefix = prefix + c;
            generatePasswords(newPrefix, length - 1, password);
        }
    }
}

class DictionaryCracker extends PasswordCracker {
    @Override
    protected boolean attemptCrack(String password) throws IOException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("../doc/rockyou.txt"));
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
}
