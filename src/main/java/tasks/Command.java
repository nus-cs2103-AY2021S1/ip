package tasks;

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
     * Getter for the code of the enumeration
     *
     * @return code attribute of Command
     */
    public String getCode() {
        return commandEncoding;
    }

    public String toString() {
        return commandEncoding;
    }
}
