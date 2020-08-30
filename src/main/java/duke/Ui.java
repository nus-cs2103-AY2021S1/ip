package duke;

import java.util.Scanner;

/**
 * Encapsulates user interface interactions.
 */
public class Ui {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final String instructions = "Hello! I'm duke.Duke\n"
            + "Send me a task in one of the following formats and I'll store it for you.\n"
            + "\tTodo: \"todo <description>\"\n"
            + "\tDeadline: \"deadline <description> /by <YYYY-MM-DD>\"\n"
            + "\tEvent: \"event <description> /at <YYYY-MM-DD>\"\n"
            + "Send \"list\" to see all tasks.\n"
            + "Send \"find <string of choice>\" to see all related tasks.\n"
            + "Send \"done <item number>\" to mark an item as done\n"
            + "Send \"delete <item number>\" to delete and item from the list\n"
            + "Send \"bye\" to end our conversation.";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints error message when error occurs whil loading data.
     */
    public void showLoadingError() {
        System.out.println("Error occurred while loading data.");
    }

    /**
     * Prints specified error message.
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints welcome words and instructions on how to use bot.
     */
    public void showWelcome() {
        System.out.println(logo + "\n" + instructions);
    }

    /**
     * Prints specified output message for user.
     * @param output Message for the user.
     */
    public void showOutput(String output) {
        System.out.println(output);
    }

    /**
     * Prints a dividing line.
     */
    public void showLine() {
        System.out.println("___________________________________________");
    }

    /**
     * Reads the next input line by user.
     * @return Input line by user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
