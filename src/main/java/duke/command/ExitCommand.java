package duke.command;

// Handles all the logic behind any "exit" command from the user
public class ExitCommand extends Command {
    private static final String EXIT_MSG = "Bye. Hope to see you again soon!";
    /**
     * Executes any "exit" command issued by the user.
     * Returns an exit message to the users.
     *
     * @return String "exit" message to user.
     */
    public static String execute() {
        return EXIT_MSG;
    }
}
