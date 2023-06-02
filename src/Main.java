import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        String option = "";
        String hash = "";
        Hashing myHash;

        while (userChoice != 1 && userChoice != 2) {
            System.out.println("\n\n#### Veuillez choisir une option #####\n\n1_Brute Force \n2_Dictionnary\n\n");
            userChoice = scanner.nextInt();
        }

        if (userChoice == 1) {
            option = "brute";
        } else if (userChoice == 2) {
            option = "dictionnary";
        }

        scanner.nextLine();  

        while (hash.equals("")) {
            System.out.println("\nVeuillez donner le hash:");
            hash = scanner.nextLine();
        }

        System.out.println(hash);
        myHash = Fabrique.getHashing(option);

        System.out.println("le mot de passe correspondant est :"+myHash.getPassword(hash)); 
    }
}
