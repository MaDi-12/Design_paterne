public class Fabrique {
    public static Hashing getHashing(String type) {
        Hashing myHash = null;
        if (type.equals("dictionary")) {
            myHash = new HashDictionnary();
        }
        if (type.equals("brute")) {
            myHash = new BruteForce();
        }
        return myHash;
    }

    public static PasswordCracker getPassword(String type) {
        PasswordCracker passwordCracker = null;
        if (type.equals("dictionary")) {
            passwordCracker = new DictionaryCracker();
        }
        if (type.equals("brute")) {
            passwordCracker = new BruteForceCracker();
        }
        return passwordCracker;
    }
}
