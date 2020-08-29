package duke.ui;

/**
 * Represents the interactions between Focus and user.
 */
public class UI {
    /**
     * Divider.
     */
    private final String divider =
            "------------------------------------------------------------------\n";

    /**
     * Prints the divider.
     */
    public void printDivider() {
        System.out.print(divider);
    }

    /**
     * Prints divider with next line.
     */
    public void printDividerWithSpacing() {
        System.out.println(divider);
    }

    /**
     * Greets the user upon entering the Focus.
     */
    public static String greetUser() {
        String logo = " __\n"
                    + "/ _|   ___    ___  _   _  ___\n"
                    + "| |_  / _ \\  / __|| | | |/ __|\n"
                    + "|  _|| (_) || (__ | |_| |\\__ \\ _\n"
                    + "|_|   \\___/  \\___| \\__,_||___/(_)\n\n";
        String greetings = "Welcome to\n" + logo + "\"\\(*^O^*) I am Pocus, your personal assistant!"
                + "\nBefore we start, may I know your name?\n";
        return greetings;
    }

    /**
     * Addresses the user with the name provided.
     * @param name Name of user.
     */
    public static String addressUser(String name) {
        String address = "Hello there, " + name + "! How can I help you today?\n"
                + "Note: If you are a first time user,\n"
                + "type 'help' for the list of commands to get started!\n";
        return address;
    }

    /**
     * List out the commands that the user can use.
     */
    public static String listCommands() {
        String commands = "Here are the commands you can use:"
                + "\n\tTo add tasks:"
                + "\n\t\t- todo <todo task name>"
                + "\n\t\t- deadline <deadline task name> /by <YYYY-MM-DD HH:mm>"
                + "\n\t\t- event <event task name> /at <YYYY-MM-DD HH:mm>"
                + "\n\tTo delete tasks:"
                + "\n\t\t- delete <index of task>"
                + "\n\tTo mark tasks as done:"
                + "\n\t\t- done <index of task>"
                + "\n\tTo list the tasks on your task list:"
                + "\n\t\t- list"
                + "\n\tTo find tasks on your task list:"
                + "\n\t\t- find <keyword>"
                + "\n\tTo exit Focus:"
                + "\n\t\t- bye\n";
        return commands;
    }

    /**
     * Exits Focus.
     */
    public String exitFocus() {
        String exit = "Hopefully I have helped you today. Byeee! (*^O^*)/\"\n";
        return exit;
    }
}
