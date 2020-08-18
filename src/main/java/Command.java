public enum Command {
    BYE("bye"),
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    LIST("list"),
    TODO("todo");

    private String value;

    private Command(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
