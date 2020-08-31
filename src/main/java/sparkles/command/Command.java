package sparkles.command;

import sparkles.SparklesException;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Abstract class that represents a Command.
 */
public abstract class Command {

    protected final String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException;

    /**
     * Check is the command is an exit command.
     *
     * @return boolean indicating true or false
     */
    public boolean isExit() {
        return false;
    };
}
