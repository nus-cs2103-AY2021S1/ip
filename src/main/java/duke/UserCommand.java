package duke;

/**
 * Encapsulates all the commands recognised by chatBot.
 */
public enum UserCommand {
    BYE("bye"),
    LIST("list"), LS("ls"),
    DONE("done"),
    TODO("todo"), T("t"),
    DEADLINE("deadline"), D("d"),
    EVENT("event"), E("e"),
    DELETE("delete"),
    CLEAR("clear"),
    FIND("find");

    private final String command;

    /**
     * Constructor for instantiating a user command.
     *
     * @param command command that the user types in
     */
    UserCommand(String command) {
        this.command = command;
    }

    /**
     * Get method for user command.
     *
     * @return the user command
     */
    String getCommand() {
        return this.command;
    }
}
