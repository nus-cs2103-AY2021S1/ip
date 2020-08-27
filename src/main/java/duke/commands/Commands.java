package duke.commands;

/**
 * Enum to represent the different the user commands.
 */
public enum Commands {
    LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"),
    DONE("done"), DELETE("delete"), BYE("bye");

    private final String command;

    Commands(String command) {
        this.command = command;
    }

    /**
     * Check if the given input matches the Command's command.
     * @param input input to check
     * @return true if input matches the command
     */
    public boolean check(String input) {
        return command.equals(input);
    }
}