package duke;

import java.util.Scanner;

public class Ui {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    
    private final String instructions = "Hello! I'm duke.Duke\n" +
            "Send me a task in one of the following formats and I'll store it for you.\n" +
            "\tTodo: \"todo <description>\"\n" +
            "\tDeadline: \"deadline <description> /by <YYYY-MM-DD>\"\n" +
            "\tEvent: \"event <description> /at <YYYY-MM-DD>\"\n" +
            "Send \"list\" to see all tasks.\n" +
            "Send \"done <item number>\" to mark an item as done\n" +
            "Send \"delete <item number>\" to delete and item from the list\n" +
            "Send \"bye\" to end our conversation.";

    private final Scanner scanner = new Scanner(System.in);
    
    public void showLoadingError() {
        System.out.println("Error occurred while loading data.");
    }
    
    public void showError(String message) {
        System.out.println(message);
    }
    
    public void showWelcome() {
        System.out.println(logo + "\n" + instructions);
    }
    
    public void showOutput(String output) {
        System.out.println(output);
    }
    
    public void showLine() {
        System.out.println("___________________________________________");
    }
    
    public String readCommand() {
        return scanner.nextLine();
    }
}
