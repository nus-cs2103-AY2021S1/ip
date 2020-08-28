package duke.commands;


/**
 * Command enum.
 */
public enum Command {
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TERMINATE("terminate"),
    TASK("task"),
    FIND("find"),
    INVALID("invalid");

    private final String str;


    Command(String str) {
        this.str = str;
    }
}
