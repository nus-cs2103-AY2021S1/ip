package duke.task;

public enum CommandType {
    LIST("list"),
    SHOW("show"),
    BYE("bye"),
    DELETE("delete"),
    DONE("done"),
    ADD("add"),
    FIND("find");

    /**
     * Type of Command.
     */
    private String type;

    private CommandType(String type) {
        this.type = type;
    }

    /**
     * Returns a String representation of the {@link duke.command.Command} type.
     * @return A String of the {@link duke.command.Command} type.
     */
    public String getType() {
        return type;
    }
}
