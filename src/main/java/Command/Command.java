package Command;

import TaskList.*;

/**
 * A class to handle user commands.
 */
public abstract class Command {
    CommandType type;

    Command(CommandType type) {
        this.type = type;
    }

    public enum CommandType {
        EXIT, DONE, DELETE, LIST, FIND, ADD, ERROR;
    }

    public CommandType getType() {
        return type;
    }

    public abstract void act(TaskList list);
}
