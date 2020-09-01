package duke.commands;

/**
 * Represents the different types of commands that the system can interpret
 */
public enum CommandType {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    FIND("find"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String name;

    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
