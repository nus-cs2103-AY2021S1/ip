public enum Command {
    LIST("list"),
    DEADLINE("deadline"),
    TODO("todo"),
    EVENT("event"),
    DONE("done"),
    BYE("bye"),
    DELETE("delete");

    private String name;

    Command(String name) {
        this.name = name;
    }

    public boolean is(String cmd) {
        return cmd.startsWith(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
