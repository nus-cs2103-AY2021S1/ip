package duke.command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.InvalidInputException;

public class DoneCommand extends Command {
    private Task task;
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        Task task = taskList.getTask(index);
        taskList.setAsDone(index);
        storage.write(taskList.getListOfTasks());
        ui.displayDone(task);
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

