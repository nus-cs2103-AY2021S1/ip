import exceptions.CommandException;

import java.util.stream.Stream;

public enum Command {
    EXIT_CMD    ("bye", true),
    LIST_CMD    ("list", true),
    DONE_CMD    ("done", false),
    TODO_CMD    ("todo", false),
    EVENT_CMD   ("event", false),
    DEADLINE_CMD("deadline", false),
    DELETE_CMD  ("delete", false);

    private final String cmdString;
    private final boolean noArgs;

    Command(String cmdString, boolean noArgs) {
        this.cmdString = cmdString;
        this.noArgs = noArgs;
    }

    public String getCmdString() {
        return cmdString;
    }

    @Override
    public String toString() {
        return cmdString;
    }

    public String strip(String input) {
        return input.substring(cmdString.length()).strip();
    }

    public static Command parseCommand(String input) throws CommandException {
        return Stream.of(values())
                .filter(cmd -> cmd.noArgs
                        ? input.equals(cmd.cmdString)
                        : input.startsWith(cmd.cmdString))
                .findFirst()
                .orElseThrow(() -> new CommandException(input, "Unknown command"));
    }

}