package duke;

/**
 * An enumeration of different types of existing commands
 * for duke.
 */
public enum DukeCommand {
    LIST("list", 100), DATE("date", 101), FIND("find", 102), CLEARALL("clearall", 102),

    TODO("todo", 200), DEADLINE("deadline", 201), EVENT("event", 201),

    DONE("done", 300), DELETE("delete", 301),

    BYE("bye", 400);

    private String command;
    private int index;

    private DukeCommand(String command, int index) {
        this.command = command;
        this.index = index;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
