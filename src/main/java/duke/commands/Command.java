package duke.commands;

public enum Command {
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TERMINATE("terminate"),
    TASK("task"),
    INVALID("invalid");
    
    private final String str;


    private Command(String str) {
        this.str = str;
    }
}
