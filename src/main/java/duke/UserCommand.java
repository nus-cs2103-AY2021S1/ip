package duke;

/**
 * Encapsulates all the commands recognised by chaBot
 */
public enum UserCommand {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    CLEAR("clear");

    private final String command;

    /**
     * Constructor for instantiating a user command.
     * @param command command that the user types in
     */
    UserCommand(String command) {
        this.command = command;
    }

    /**
     * Gett method for user command
     * @return the user command
     */
    String getCommand() {
        return this.command;
    }
}
