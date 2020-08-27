package duke.commands;

public enum Commands {
    LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"),
    DONE("done"), DELETE("delete"), BYE("bye"), FIND("find");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    public boolean check(String input) {
        return command.equals(input);
    }
}