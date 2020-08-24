package duke;

public enum Command {
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    BYE("bye"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    public final String cmd;

    Command(String cmd) {
        this.cmd = cmd;
    }

    public static Command valueOfUserCommand(String userCommand) {
        for (Command e : values()) {
            if (e.cmd.equals(userCommand)) {
                return e;
            }
        }
        return null;
    }


}
