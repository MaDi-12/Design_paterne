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
}
