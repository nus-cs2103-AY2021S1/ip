package duke.command;

/**
 * Represents command type with input format.
 */
public enum CommandType {
    LIST("list"), BYE("bye"), DELETE("delete"), DONE("done"),
    TODO("todo"), DEADLINE("deadline"), EVENT("event"), FIND("find");

    private final String input;

    /**
     * Initialises a command type with the input format.
     *
     * @param input Input format.
     */
    CommandType(String input) {
        this.input = input;
    }

    /**
     * Gets input format.
     *
     * @return Input format.
     */
    public String getInput() {
        return input;
    }
}
