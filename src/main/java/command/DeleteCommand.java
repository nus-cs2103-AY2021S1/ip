package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.InvalidInputException;
import task.Task;

/**
 * Class to initiate the Delete Command
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for delete command
     *
     * @param index index of the task to delete.
     */
    public DeleteCommand(int index) {
        super(CommandType.Delete);
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index != 0 && index <= taskList.getSize()) {
            Task task = taskList.getTask(index - 1);
            taskList.removeTask(index - 1);
            return ui.showDelete(task, taskList.getSize());
        } else {
            throw new InvalidInputException(
                    "Number provided is too small or too large, Please provide a valid task number");
        }
    }
}

