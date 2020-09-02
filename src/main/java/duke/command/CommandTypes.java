package duke.command;

/**
 * Represents the set of strings which are legal as command types.
 */
public enum CommandTypes {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find");

    final String command;

    /**
     * Creates a new command type from the user.
     * @param command is one the command types from the user.
     */
    CommandTypes(String command) {
        this.command = command;
    }
}
