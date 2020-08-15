package main.java;

public class Chat {

    // Returns a printable chatBox around a String
    public static String chatBox(String chatContent){
        return "\t== Duke says =====================\n" +
                "\t " + chatContent + "\n" +
                "\t==================================\n";
    }
}
