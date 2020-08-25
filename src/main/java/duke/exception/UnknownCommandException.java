package duke.exception;

/**
 * Thrown when the user inputs a command which is not part of the known commands.
 * Proceeds to show the list of valid commands to the user.
 */
public class UnknownCommandException extends DukeException {

    private static final int NUM_OF_COMMANDS = 7;
    private static final String MSG_1 = "I'm sorry, but I don't know what that means.\n";
    private static final String MSG_2 = "    Here are the available commands that I know:\n";

    /**
     * Initializes the UnknownCommandException object.
     */
    public UnknownCommandException() {
        super(UnknownCommandException.getListOfCommands());
    }

    private static final String[] listOfCommands = new String[]{
            "todo _ (e.g. todo 3)",
            "deadline 'task name' /by 'end time' (e.g. deadline Exercise /by Sunday)",
            "'event name' /at 'start time - end time' (e.g. meeting /at Sunday 2pm - 4pm)",
            "list",
            "done _ (e.g. done 4)",
            "delete _ (e.g. delete 4)",
            "find '   ' (e.g. find book)",
            "bye",
    };

    private static String getListOfCommands() {
        StringBuilder str1 = new StringBuilder();
        str1.append(MSG_1);
        str1.append(MSG_2);
        for (int i = 1; i < NUM_OF_COMMANDS; i++) {
            String s = String.format("     %d. %s\n", i, listOfCommands[i - 1]);
            str1.append(s);
        }
        str1.append(String.format("     %d. %s", NUM_OF_COMMANDS, listOfCommands[NUM_OF_COMMANDS - 1]));
        return str1.toString();
    }
}
