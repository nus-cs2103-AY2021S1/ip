package duke.command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidInputException;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        ui.getList();
    }

    /**
     * Checks if the command is ExitCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
