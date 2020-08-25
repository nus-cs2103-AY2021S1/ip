/**
 * Represents the different types of command that the user can input
 */
public enum CommandType {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String name;
    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
