package duke.command;

import java.util.Queue;

import duke.action.Action;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a call to delete a Task from TaskList.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private final int taskNumber;

    /**
     * Constructor for DeleteCommand.
     * @param taskNumber Task number of Task in TaskList.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks,
                        Queue<Action> commandQueue) throws DukeException {
        boolean taskNumberGreaterThanZero = taskNumber <= 0;
        boolean taskNumberMoreThanTaskListSize = taskNumber > tasks.getNumOfTasks();
        boolean isNotValidTaskNumber = taskNumberGreaterThanZero
                || taskNumberMoreThanTaskListSize;

        if (isNotValidTaskNumber) {
            throw new DukeException("Task does not exist/invalid task number.");
        }

        Task t = tasks.retrieve(taskNumber);
        tasks.remove(taskNumber);
        ui.deleteTaskMessage(t, tasks);
    }
}
