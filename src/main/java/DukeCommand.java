public enum DukeCommand {
    LIST("list", 100, ListCommand.class),DATE("date",101, DateCommand.class),

    TODO("todo", 200, TodoCommand.class),DEADLINE("deadline", 201, DeadlineCommand.class),EVENT("event", 201, EventCommand.class),

    DONE("done", 300, DoneCommand.class),DELETE("delete", 301, DeleteCommand.class),

    BYE("bye", 400, ByeCommand.class);

    private String command;
    private int index;
    private Class<?> commandClass;

    private DukeCommand(String command, int index, Class<?> commandClass) {
        this.command = command;
        this.index = index;
        this.commandClass = commandClass;
    }

    public String getCommand() { return command; }

    public void setCommand(String command) { this.command = command; }

    public int getIndex() { return index; }

    public void setIndex(int index) { this.index = index; }

    public Class<?> getCommandClass() { return commandClass; }

    public void setCommandClass(Class<?> commandClass) { this.commandClass = commandClass; }
}
