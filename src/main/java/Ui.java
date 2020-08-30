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
     */
    public static void showLine() {
        System.out.println("\n_________________________________________\n");
    }

    public static void showCommandMessage(String message) {
        System.out.println("_________________________________________\n" + message);
    }

    /**
     * Prints out the Welcome Message when Duke is intialised
     * as an opening message
     */
    public static void welcomeMessage() {
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
        System.out.println("INITIALIZING \n" + logo);
        System.out.println("Current version: " + version);
        System.out.println("Welcome to DUKE, the ToDo list creator \n"
                + "We current support these commands: \n" + commands);
        System.out.println("Please input a command:");
    }

    /**
     * Prints out the Goodbye Message when Duke is exited
     */
    public static void goodbyeMessage() {
        System.out.println("_________________________________________\n"
                + "Bye. Hope to see you again soon!" + "\n"
                + "_________________________________________");
    }

    /**
     * Print out the error message when an exception is catch
     * by wrapping it in separating lines to format the message.
     *
     * @param error The error message
     */
    public static void showError(String error) {
        System.out.println("Sorry an unexpected error occured!! :( \n" +
                "_________________________________________\n"
                + error
                + "\n_________________________________________");
    }

    public static void showResponse(String response, String command) {
        System.out.println("Task: " + command + "\nExecution Success: \n" +
                "_________________________________________\n"
                + response
                + "\n_________________________________________");
    }

    public static void showSaving() {
        System.out.println("Current saving tasks. Do not Exit!");
    }
}
