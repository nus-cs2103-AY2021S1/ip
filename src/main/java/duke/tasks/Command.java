package duke.tasks;

/**
 * Command is a Storage for the Enumerations of each type of command that is valid in Duke Chatbot.
 */
public enum Command {
    //random string as this is the default enum.
    ERROR("asjdbaksjfbanfjknjkdfnskasd"),
    BYE("bye"),
    HELP("help"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    SEARCH("find"),
    BLANK("");
    private final String commandEncoding;

    /**
     * Constructor for the enumeration for commands
     * @param code
     */
    Command(String code) {
        this.commandEncoding = code;
    }
    /**
     * Getter for command that is encoded in a Command
     * @return exact text that represents a command in Duke
     */
    public String getCode() {
        return commandEncoding;
    }
    public String toString() {
        return commandEncoding;
    }
}
