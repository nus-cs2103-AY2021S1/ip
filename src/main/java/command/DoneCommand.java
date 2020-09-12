package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.InvalidInputException;
import task.Task;

/**
 * Class to initiate the Done Command
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructor of the Done Command
     *
     * @param index index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        super(CommandType.Done);
        this.index = index;
    }

    /**
     * Runs the command to mark the selected Task as done
     *
     * @param taskList ArrayList of Tasks Objects.
     * @param ui       Object of the Ui class.
     * @param storage  Object of the Storage class.
     * @throws DukeException Exception that occurs while executing the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index != 0 && index <= taskList.getSize()) {
            Task task = taskList.getTask(index - 1);
            task.markAsDone();
            return ui.showDone(task);
        } else {
            throw new InvalidInputException(
                    "Number provided is too small or too large, Please provide a valid task number");
        }
    }
}
