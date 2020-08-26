package command;
import exception.DukeException;
import tasklist.TaskList;
import ui.Ui;
import storage.Storage;

public abstract class Command {
    public Command(){}

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
