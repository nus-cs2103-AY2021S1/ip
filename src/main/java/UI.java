package main.java;

import java.util.List;

public class UI {

    // Prints the welcome message
    public static String welcome(){
        String logo =
                " ____  __.__\n" +
                        "|    |/ _|__| ____    ____\n" +
                        "|      < |  |/    \\  / ___\\ \n" +
                        "|    |  \\|  |   |  \\/ /_/  >\n" +
                        "|____|__ \\__|___|  /\\___  /\n" +
                        "        \\/       \\//_____/\n";
        return logo + "\n" + "Hello! I'm King\nWhat can I do for you?";
    }

    // Returns a printable chatBox around a single-line string
    public static String chatBox(String chatContent){
        return "\t== King says =====================\n" +
                "\t " + chatContent + "\n" +
                "\t==================================\n";
    }

    // Returns a printable error message box around the error String
    public static String errorBox(String error){
        return "-------- Error Encountered -------------------------------------\n" +
                "\t " + error + "\n" +
                "----------------------------------------------------------------\n";
    }

    // Creates a chat box when item is added
    public static String addItemChatBox(String chatContent, int noOfItems){
        return "\t== King says =====================\n" +
                "\t Got it. I've added this task:\n" +
                "\t\t" + chatContent + "\n" +
                "\t Now you have " + noOfItems + " tasks in the list.\n" +
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
