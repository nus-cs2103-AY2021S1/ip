public enum Command {
    LIST("list"),
    BYE("bye"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    VIEW("view");

    private final String command;

    Command(String command) {
        this.command = command;
    }
}
