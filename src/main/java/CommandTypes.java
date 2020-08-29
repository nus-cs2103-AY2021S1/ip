public enum CommandTypes {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String command;

    CommandTypes(String command) {
        this.command = command;
    }
}
