package command;

/**
 * Enumerates the types of possible commands.
 */
public enum Command {
    EXIT_CMD    ("bye", true),
    LIST_CMD    ("list", true),
    DONE_CMD    ("done", false),
    TODO_CMD    ("todo", false),
    EVENT_CMD   ("event", false),
    DEADLINE_CMD("deadline", false),
    DELETE_CMD  ("delete", false);

    private final String cmdString;
    private final boolean noArgs;

    Command(String cmdString, boolean noArgs) {
        this.cmdString = cmdString;
        this.noArgs = noArgs;
    }

    /**
     * Returns the command string that triggers this command.
     * @return a command string keyword e.g. {@code list}, {@code done}
     */
    public String getCmdString() {
        return cmdString;
    }

    /**
     * Checks if the command is standalone and has no arguments.
     * @return {@code true} if the command has no arguments
     */
    public boolean isNoArgs() {
        return noArgs;
    }

    @Override
    public String toString() {
        return cmdString;
    }

    /**
     * Strips the first few characters of the given String,
     * depending on the length of the command string.
     * This is used to obtain arguments from a command.
     * @param input the String to strip
     * @return the stripped input string
     */
    public String strip(String input) {
        return input.substring(cmdString.length()).strip();
    }

}