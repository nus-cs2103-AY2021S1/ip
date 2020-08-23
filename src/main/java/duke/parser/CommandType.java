package duke.parser;

/**
 * Represents the various commands that Duke supports.
 */
public enum CommandType {
    BYE("bye", 1),
    LIST("list", 1),
    DONE("done", 2),
    TODO("todo", 2),
    DEADLINE("deadline", 4),
    EVENT("event", 4),
    DELETE("delete", 2),
    FIND("find", 2);

    private final String command;
    private final int commandLength;

    /**
     * Constructor class.
     * @param command the String of the actual command.
     * @param commandLength the minimum number of parameters needed for the command.
     *                      This includes the commandType.
     */
    CommandType(String command, int commandLength) {
        this.command = command;
        this.commandLength = commandLength;
    }

    /**
     * Gets the command type.
     * @return a String of the command type.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Checks if the given length is smaller than the commandLength.
     * If such, it also means that the command is invalid.
     * @return true if the given length is greater than or equal to the commandLength.
     */
    public boolean isLengthSmaller(int length) {
        return this.commandLength > length;
    }
}
