package tasks;

/**
 * Command is a Storage for the Enumerations of each type of command that is valid in Duke Chatbot
 */
public enum Command {
    ERROR("asjdbaksjfbanfjknjkdfnskasd"),//random string as this is the default enum
    BYE("bye"),
    HELP("help"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BLANK("");
    private final String commandEncoding;

    Command(String code) {
        this.commandEncoding = code;
    }
    
    /**
     * Getter for command that is encoded in a Command
     * @return exact text that represents a command in Duke
     */
    public String  getCode() {
        return commandEncoding;
    }

    public String toString() {
        return commandEncoding;
    }
}
