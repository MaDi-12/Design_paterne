public class Fabrique {
    public static Hashing getHashing(String type){
        Hashing myHash = null;
        if (type.equals("dictionnary")) {
            myHash= new HashDictionnary();
        }
        if (type.equals("brute")) {
            myHash= new BruteForce();
        }

        return myHash;

    }
      public static PasswordCracker getPassword(String type){
        PasswordCracker passwordCracker = null;
        if (type.equals("Pdictionnary")) {
            passwordCracker = new DictionaryCracker();
        }
        if (type.equals("Pbrute")) {
            passwordCracker = new BruteForceCracker();
        }
        
        return passwordCracker;

    }
}
