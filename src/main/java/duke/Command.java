package duke;

/**
 * This class stores the various commands allowed by Duke
 */
public enum Command {
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    BYE("bye"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find");

    public final String cmd;

    Command(String cmd) {
        this.cmd = cmd;
    }

    /**
     * This method checks the user command and links it to the command values stored.
     *
     * @param userCommand The input command string by the user.
     * @return returns a command enum that has string values in it.
     */
    public static Command valueOfUserCommand(String userCommand) {
        for (Command e : values()) {
            if (e.cmd.equals(userCommand)) {
                return e;
            }
        }
        return null;
    }


}
