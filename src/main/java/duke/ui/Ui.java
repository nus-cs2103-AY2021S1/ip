package duke.ui;

/**
 * <h1>Ui Class</h1>
 * The Ui class manages the interactions with
 * the User.
 * It consist of mainly void
 * methods that display a formatted text that is readable
 * by the User.
 *
 * @author Lee Penn Han
 * @version 1.0
 * @since 2020-08-25
 */
public class Ui {

    /**
     * Prints out a line to mark an individual message
     * @return String
     */
    public static String showLine() {
        return "_________________________________________\n";
    }

    /**
     * Prints out a line and a message
     * @param message String message
     * @return String
     */
    public static String showCommandMessage(String message) {
        return "_________________________________________\n" + message + "\n";
    }

    /**
     * Prints out the Welcome Message when Duke is initialised
     * as an opening message
     * @return String
     */
    public static String welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String version = "Version 1.0";
        String indent = "        ";
        String commands = indent + "list: Shows the current List \n"
                + indent + "delete (index): Delete the task from the List \n"
                + indent + "todo (activity): Creates a Todo Task \n"
                + indent + "deadline /by (date) YYYY-MM-DD: Creates a Deadline Task \n"
                + indent + "event /at (string): Creates an Event Task \n"
                + indent + "find (keyword): Finds all occurrences of the keyword in the List";
        return "INITIALIZING \n" + logo
                + "\nCurrent version: " + version
                + "\nWelcome to DUKE, the ToDo list creator "
                + "\nWe current support these commands: \n" + commands
                + "\nPlease input a command:";
    }

    /**
     * Prints out the Goodbye Message when Duke is exited
     * @return String
     */
    public static String goodbyeMessage() {
        return "_________________________________________\n"
                + "Closing now, hope to see you again soon!\n"
                + "_________________________________________";
    }

    /**
     * Print out the error message when an exception is catch
     * by wrapping it in separating lines to format the message.
     * @param error The error message
     * @return String
     */
    public static String showError(String error) {
        return "Sorry an error occurred!! \n"
                + "_________________________________________\n"
                + error + "\n"
                + "_________________________________________";
    }

    /**
     * Prints a formatted response of a successful Task execution
     * @param response The String representation of the response from the command input
     * @param command The String command from the command from User Input
     * @return String
     */
    public static String showResponse(String response, String command) {
        return "Task: " + command + "\nExecution Success: \n"
                + "_________________________________________\n"
                + response
                + "\n_________________________________________";
    }

    /**
     * Prints a formatted response to inform the user that saving is in progress.
     * @return String
     */
    public static String showSaving(String summary) {
        return "Currently saving tasks. Do not Exit! \n" + summary + "\n";
    }
}
