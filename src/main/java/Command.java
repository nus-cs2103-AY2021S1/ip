public enum Command {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String name;
    Command(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
