import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        String option = "";
        String hash = "";
        Hashing myHash;
        PasswordCracker myPassword;
        String mot_de_passe;


    

        while (userChoice <= 0 || userChoice > 3) {
            System.out.println("\n\n#### Veuillez choisir une option #####\n\n1_Cracker un mot de passe \n2_Cracker un hash\n 3_Cracker par requette HTTP\n\n");
            userChoice = scanner.nextInt();
        }

        if (userChoice==2) {
            userChoice=0;
            while (userChoice <= 0 || userChoice > 2) {
                System.out.println("\n\n#### Veuillez choisir une option #####\n\n1_Brute Force \n2_Dictionnary\n\n");
                userChoice = scanner.nextInt();
            }
            switch (userChoice) {
                case 1:
                    option = "brute";
                    break;
                case 2:
                    option = "dictionnary";
                    break;
            }
            scanner.nextLine();  

            while (hash.equals("")) {
                System.out.println("\nVeuillez donner le hash du mot:");
                hash = scanner.nextLine();
            }

            System.out.println(hash);
            myHash = Fabrique.getHashing(option);
            System.out.println("le mot de passe correspondant est :"+myHash.getPassword(hash));
        }
        else if (userChoice==1){
            userChoice = 0;
            while (userChoice <= 0 || userChoice > 2) {
                System.out.println("Choisissez une option pour cracker un mot de passe :");
                System.out.println("1. Cracker un mot de passe en utilisant Brute Force");
                System.out.println("2. Cracker un mot de passe en utilisant Dictionnaire Attack");
                userChoice = scanner.nextInt();
            }
            switch (userChoice) {
                case 2 :
                    option = "Pdictionnary";
                    break;
                case 1:
                    option = "Pbrute";
                    break;
            }
            scanner.nextLine(); //pour vider le tampon
            System.out.print("Veuillez saisir votre mot de passe : ");
            mot_de_passe = scanner.nextLine();
            myPassword=Fabrique.getPassword(option);
            myPassword.crackPassword(mot_de_passe);
        }
        else{
            userChoice = 0;
            while (userChoice <= 0 || userChoice > 2) {
                System.out.println("Choisissez une option pour cracker  :");
                System.out.println("1. Cracker un mot de passe en utilisant Brute Force");
                System.out.println("2. Cracker un mot de passe en utilisant Dictionnaire Attack");
                userChoice = scanner.nextInt();
            }
            switch (userChoice) {
                case 2 :
                    option = "Pdictionnary";
                    break;
                case 1:
                    option = "Pbrute";
                    break;
            }
            myPassword=Fabrique.getPassword(option);
            System.out.println(myPassword.getPassword());
        
        }
       
        
    }
}
