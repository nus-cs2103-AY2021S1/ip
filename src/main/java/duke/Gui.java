package duke;

/**
 * Represents the graphical user interface.
 */
public class Gui {

    /**
     * Shows the loading error.
     * @return String stating the loading error.
     */
    public String showLoadingError() {
        return "The test file cannot be loaded properly.";
    }

    /**
     * Shows the exit statement.
     * @return String stating the exit statement.
     */
    public String printExitBot() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows the no query result statement.
     * @return String stating the no query result statement.
     */
    public String printNoQueryResult() {
        return "We are unable to find any task that match your query.";
    }

    /**
     * Shows the help interface.
     * @return String showing the help interface.
     */
    public String printHelpInterface() {
        String helpInterface = "List of available commands: \ntodo - creates a todo (e.g todo read book)" +
                "\ndeadline - creates a deadline with a date (e.g deadline return book /by 2019-10-15)" +
                "\nevent - creates an event with a date (e.g event go library /at 2019-10-15)" +
                "\ndone - sets item at index to done (e.g done 1)" +
                "\ndelete - deletes item at index (e.g delete 1)" +
                "\nfind - finds task (e.g find book)";
        return helpInterface;
    }
}
