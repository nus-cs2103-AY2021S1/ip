public enum CommandType {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find");

    private final String name;
    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
