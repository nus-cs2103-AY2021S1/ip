package main.java;
import java.util.List;

public class UI {

    // Prints the welcome message
    public static String welcome(){
        String logo =
                " ____  __.__\n" +
                        "|    |/ _|__| ____    ____\n" +
                        "|      < |  |/    \\  / ___\\\n" +
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

    // Returns a printable chatBox numbering each item in given a TaskList
    public static String listTaskList(TaskList taskList){
        StringBuilder formatted = new StringBuilder();
        int len = taskList.size();
        for (int number = 0; number < len; number++){
            formatted.append("\t" + (number+1) + ". " + taskList.get(number).toString() + "\n");
        }
        return "\t== King says =====================\n" +
                "\tThere are " + len + " items in your list:\n" +
                formatted.toString() +
                "\t==================================\n";
    }
}
