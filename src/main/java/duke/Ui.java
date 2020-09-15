package duke;

/**
 * Encapsulates user interface interactions.
 */
public class Ui {

    private final String instructions = "Hello! I'm Duke\n"
            + "Send me a task in one of the following formats and I'll store it for you.\n"
            + "\tTodo: \"todo <description>\"\n"
            + "\tDeadline: \"deadline <description> /by <YYYY-MM-DD>\"\n"
            + "\tEvent: \"event <description> /at <YYYY-MM-DD>\"\n"
            + "Send \"list\" to see all tasks.\n"
            + "Send \"find <string of choice>\" to see all related tasks.\n"
            + "Send \"done <item number>\" to mark an item as done\n"
            + "Send \"delete <item number>\" to delete and item from the list\n"
            + "Send \"bye\" to end our conversation.\n"
            + "Send \"help\" to end see the commands again.";

    /**
     * Prints error message when error occurs while loading data.
     */
    public void showLoadingError() {
        System.out.println("Error occurred while loading data.");
    }

    /**
     * Shows welcome message.
     * @return The welcome message by duke.
     */
    public String showWelcome() {
        return instructions + "\n";
    }

    /**
     * Prints specified output message for user.
     * @param output Message for the user.
     * @return The output by Duke.
     */
    public String showOutput(String output) {
        return output;
    }

    /**
     * Gets instruction message.
     * @return The instruction message.
     */
    public String getInstructions() {
        return instructions;
    }
}
