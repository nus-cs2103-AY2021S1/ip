package command;

/**
 * Enumerates the types of possible commands.
 */
public enum Command {
    EXIT_CMD("bye", true,
            "Exits from iPbot."),
    LIST_CMD("list", true,
            "Lists all tasks."),
    DONE_CMD("done", false,
            "Marks a task as done. Usage: done <Task ID>"),
    TODO_CMD("todo", false,
            "Creates a new Todo. Usage: todo <Task Description>"),
    EVENT_CMD("event", false,
            "Creates a new Event. Usage: event <Task Description> /at <Event Time>"),
    DEADLINE_CMD("deadline", false,
            "Creates a new Deadline. Usage: deadline <Task Description> /by <Time Due>"),
    DELETE_CMD("delete", false,
            "Deletes a task from the list. Usage: delete <Task ID>"),
    FIND_CMD("find", false,
            "Finds all tasks containing the query string. Usage: find <Query String>"),
    HELP_CMD("help", true,
            "Displays this help message.");

    private final String cmdString;
    private final boolean hasNoArgs;
    private final String helpMessage;

    Command(String cmdString, boolean hasNoArgs, String helpMessage) {
        this.cmdString = cmdString;
        this.hasNoArgs = hasNoArgs;
        this.helpMessage = helpMessage;
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
    public boolean hasNoArgs() {
        return hasNoArgs;
    }

    @Override
    public String toString() {
        return cmdString;
    }

    public String getHelpMessage() {
        return cmdString + " - " + helpMessage;
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
