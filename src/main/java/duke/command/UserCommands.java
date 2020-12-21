package duke.command;

/**
 * Represents the different types of user commands recognized by the program.
 */
enum UserCommands {
    TODO("todo"), DEADLINE("deadline"), EVENT("event"),
    DELETE("delete"), LIST("list"), DONE("done"),
    FIND("find"), EXIT("bye");

    private final String commandWord;

    /**
     * Represents a user command with the specified command word.
     *
     * @param commandWord
     */
    UserCommands(String commandWord) {
        assert commandWord != null;
        this.commandWord = commandWord;
    }

    public String getCommandWord() {
        return this.commandWord;
    }
}
