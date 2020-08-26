package duke.command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidInputException;

public abstract class Command {

    public abstract void execute(Storage storage, TaskList listOfTasks, Ui ui) throws InvalidInputException;

    public abstract boolean isExit();

}
