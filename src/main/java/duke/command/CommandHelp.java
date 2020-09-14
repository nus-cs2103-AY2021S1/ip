package duke.command;

/**
 * Command is a Storage for the Enumerations of each type of command that is valid in Duke Chatbot.
 */
public enum CommandHelp {
    //random string as this is the default enum.
    ERROR("asjdbaksjfbanfjknjkdfnskasd", "This is a invalid command"),
    BYE("bye", "close the application"),
    HELP("help", "Get help for a specific command via help -cmd command"),
    LIST("list" , "list the current list of Tasks and their statuses"),
    DONE("done" , "set a task as done via index: done 1 to mark Task 1 as done"),
    DELETE("delete", "delete a task via index: delete 1 to delete first task"),
    TODO("todo", "schedule a untimed task"),
    DEADLINE("deadline", "schedule a timed deadline task, please structure with "
            + "[deadline <task name> /by dd-MM-YYYY]"),
    EVENT("event", "schedule a timed event task, please structure with "
            + "[event <task name> /at dd-MM-YYYY]"),
    SEARCH("find", "find <regex expression> on description only"),
    BLANK("", "This is a invalid command");
    private final String commandEncoding;
    private final String helpMsg;

    /**
     * Constructs the enumeration for help commands
     * @param code key word of command
     * @param help Help message to display
     */
    CommandHelp(String code, String help) {
        commandEncoding = code;
        helpMsg = help;
    }
    /**
     * Getter for command that is encoded in a Command
     * @return exact text that represents a command in Duke
     */
    public String getCode() {
        return commandEncoding;
    }
    public String toString() {
        return "- " + commandEncoding + " to " + helpMsg + "\n";
    }

    /**
     * Checks for equivilence between the code of the command and the other
     * @param other Other command
     * @return boolean indicating that the command is the same.
     */
    public boolean equals(CommandHelp other) {
        return other.getCode().equals(getCode());
    }
}
