package sparkles.command;

import sparkles.SparklesException;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

abstract public class Command {

    protected final String command;

    protected Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException;

    public boolean isExit() {
        return false;
    };
}
