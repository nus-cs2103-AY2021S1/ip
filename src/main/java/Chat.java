package main.java;
import java.util.List;

public class Chat {

    // Returns a printable chatBox around a single-line string
    public static String chatBox(String chatContent){
        return "\t== King says =====================\n" +
                "\t " + chatContent + "\n" +
                "\t==================================\n";
    }

    // Returns a printable chatBox numbering each item in given a list
    public static <T> String numberedListChatBox(List<T> chatContent){
        StringBuilder formatted = new StringBuilder();
        int len = chatContent.size();
        if (len != 0){
            int number = 1;
            for (T item : chatContent){
                formatted.append("\t" + Integer.toString(number) + ". " + item.toString() + "\n");
                number++;
            }
        }
        return "\t== King says =====================\n" +
                "\tHere are the items in your list:\n" +
                formatted.toString() +
                "\t==================================\n";
    }
}
