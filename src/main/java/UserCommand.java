public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    String getCommand() {
        return this.command;
    }
}
