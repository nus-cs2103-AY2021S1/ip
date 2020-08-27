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
     * @param command the <code>String</code> of the actual command.
     * @param commandLength the minimum number of parameters needed for the command.
     *                      This includes the <code>command</code> itself.
     */
    CommandType(String command, int commandLength) {
        this.command = command;
        this.commandLength = commandLength;
    }

    /**
     * Gets the <code>command</code>.
     * @return a <code>String</code> of the <code>command</code>.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Checks if the given <code>length</code> is smaller than the <code>commandLength</code>.
     * If such, it also means that the <code>command</code> is invalid.
     * @return <code>rue</code> if the given length is greater than or equal to the <code>commandLength</code>.
     */
    public boolean isLengthSmaller(int length) {
        return this.commandLength > length;
    }
}
