public enum Command {
    BYE("bye"),
    COMMANDS("commands"),
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    LIST("list"),
    TODO("todo"),
    UNKNOWN("unknown");

    private String label;

    Command(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}