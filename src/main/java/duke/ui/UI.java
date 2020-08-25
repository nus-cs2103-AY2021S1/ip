package duke.ui;

/**
 * Represents the interactions between Focus and user.
 */
public class UI {
    /**
     * Divider.
     */
    private static final String divider =
            "\t------------------------------------------------------------------\n";

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
    public void greetUser() {
        String logo =
                "\t __\n"
                        + "\t/ _|   ___    ___  _   _  ___\n"
                        + "\t| |_  / _ \\  / __|| | | |/ __|\n"
                        + "\t|  _|| (_) || (__ | |_| |\\__ \\ _\n"
                        + "\t|_|   \\___/  \\___| \\__,_||___/(_)\n";
        System.out.println("Welcome to\n" + logo);
        String greetings = divider
                + "\t\"\\(*^O^*) I am Pocus, your personal assistant!"
                + "\n\tBefore we start, may I know your name?\n"
                + divider;
        System.out.println(greetings);
    }

    /**
     * Addresses the user with the name provided.
     * @param name Name of user.
     */
    public void addressUser(String name) {
        String address = divider
                + "\tHello there, " + name + "! How can I help you today?\n"
                + "\tNote: If you are a first time user,\n"
                + "\ttype 'help' for the list of commands to get started!\n"
                + divider;
        System.out.println(address);
    }

    /**
     * List out the commands that the user can use.
     */
    public static void listCommands() {
        String commands = "\tHere are the commands you can use:"
                + "\n\t-----------------------------------------------------------"
                + "\n\tTo add tasks:"
                + "\n\t\t- todo <todo task name>"
                + "\n\t\t- deadline <deadline task name> /by <YYYY-MM-DD HH:mm>"
                + "\n\t\t- event <event task name> /at <YYYY-MM-DD HH:mm>"
                + "\n\t-----------------------------------------------------------"
                + "\n\tTo delete tasks:"
                + "\n\t\t- delete <index of task>"
                + "\n\t-----------------------------------------------------------"
                + "\n\tTo mark tasks as done:"
                + "\n\t\t- done <index of task>"
                + "\n\t-----------------------------------------------------------"
                + "\n\tTo list the tasks on your task list:"
                + "\n\t\t- list"
                + "\n\t-----------------------------------------------------------"
                + "\n\tTo find tasks on your task list:"
                + "\n\t\t- find <keyword>"
                + "\n\t-----------------------------------------------------------"
                + "\n\tTo exit Focus:"
                + "\n\t\t- bye\n";
        System.out.print(commands);
    }

    /**
     * Exits Focus.
     */
    public void exitFocus() {
        String exit = "\tHopefully I have helped you today. Byeee! (*^O^*)/\"\n"
                + divider;
        System.out.println(exit);
    }
}
