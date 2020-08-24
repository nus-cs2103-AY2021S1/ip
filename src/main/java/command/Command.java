package command;

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

    public boolean isNoArgs() {
        return noArgs;
    }

    @Override
    public String toString() {
        return cmdString;
    }

    public String strip(String input) {
        return input.substring(cmdString.length()).strip();
    }

}