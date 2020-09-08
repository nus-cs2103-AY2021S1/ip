package duke.command;

public enum CommandType {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find"),
    LOAD("load"),
    CREATE("create");

    private final String label;

    CommandType(String label) {
        this.label = label;
    }
}
