public enum CommandType {
    LIST("list"), BYE("bye"), DELETE("delete"), DONE("done"),
    TODO("todo"), DEADLINE("deadline"), EVENT("event");

    private final String input;

    CommandType(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}