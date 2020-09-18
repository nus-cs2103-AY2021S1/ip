package Command;

import TaskList.TaskList;

/**
 * A class to handle user commands.
 */
public abstract class Command {
    CommandType type;

    Command(CommandType type) {
        this.type = type;
    }

    public enum CommandType {
        EXIT, DONE, DELETE, LIST, FIND, ADD, ERROR, UNDO;
    }

    public CommandType getType() {
        return type;
    }

    /**
     * Executes the command
     * @param list the task list of the application.
     * @return a String which is the message from the application.
     */
    public abstract String act(TaskList list);
    public abstract String undo(TaskList list);
}
