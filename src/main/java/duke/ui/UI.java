package duke.ui;

/** Represents the interactions between Focus and user. */
public class UI {
    /**
     * Greets the user upon entering the Focus.
     *
     * @return Greetings.
     */
    public static String greetUser() {
        String logo = "\t  __\n\t"
                    + "/ _|   ___   ___  _   _  _ __\n\t"
                    + "| |_  / _ \\ / __|  | | | | / __|\n\t"
                    + "|  _|| (_) | | (__  | |_| | \\__ \\  _\n\t"
                    + "|_|   \\___/ \\___| \\__,_||___/(_)\n\n";
        String greetings = "\tWelcome to\n" + logo + "\t\"\\(*^O^*) I am Pocus, your personal assistant!"
                + "\n\tBefore we start, may I know your name?\n";
        return greetings;
    }

    /**
     * Addresses the user with the name provided.
     *
     * @param name Name of user.
     * @return Addressing of user.
     */
    public static String addressUser(String name) {
        String address = "\tHello there, " + name + "! How can I help you today?\n"
                + "\tNote: If you are a first time user,\n"
                + "\ttype 'help' for the list of commands to get started!\n";
        return address;
    }

    /**
     * Lists out the commands that the user can use.
     *
     * @return List of commands.
     */
    public static String listCommands() {
        String commands = "\tHere are the commands you can use:"
                + "\n\t\tTo add tasks:"
                + "\n\t\t\t- todo <todo task name>"
                + "\n\t\t\t- deadline <deadline task name> /by <YYYY-MM-DD HH:mm>"
                + "\n\t\t\t- event <event task name> /at <YYYY-MM-DD HH:mm>"
                + "\n\t\tTo delete tasks:"
                + "\n\t\t\t- delete <index of task>"
                + "\n\t\tTo mark tasks as done:"
                + "\n\t\t\t- done <index of task>"
                + "\n\t\tTo list the tasks on your task list:"
                + "\n\t\t\t- list"
                + "\n\t\tTo find tasks on your task list:"
                + "\n\t\t\t- find <keyword>"
                + "\n\t\tTo exit Focus:"
                + "\n\t\t\t- bye\n";
        return commands;
    }

    /**
     * Exits Focus.
     *
     * @return Exit output.
     */
    public String exitFocus() {
        String exit = "\tHopefully I have helped you today. Byeee! (*^O^*)/\"\n"
                + "\tNote: This window close in 5 seconds.";
        return exit;
    }
}
