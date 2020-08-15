package main.java;
import java.util.List;

public class Chat {

    // Returns a printable chatBox around a single-line string
    public static String singleLineChatBox(String chatContent){
        return "\t== King says =====================\n" +
                "\t " + chatContent + "\n" +
                "\t==================================\n";
    }

    // Returns a printable chatBox numbering each item in given a string list
    public static String numberedListChatBox(List<String> chatContent){
        StringBuilder formatted = new StringBuilder();
        int len = chatContent.size();
        if (len != 0){
            int number = 1;
            for (String item : chatContent){
                formatted.append("\t" + Integer.toString(number) + ". " + item + "\n");
                number++;
            }
        }
        return "\t== King says =====================\n" +
                formatted.toString() +
                "\t==================================\n";
    }
}
