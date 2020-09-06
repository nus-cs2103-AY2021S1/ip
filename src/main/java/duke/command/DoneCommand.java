package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

import duke.storage.Storage;

import duke.exception.DukeException;

/**
 * Represents a call to mark a Task as done.
 */
public class DoneCommand extends Command {

    private final int taskNumber;

    public static final String COMMAND_WORD = "done";

    /**
     * Constructor for DoneCommand.
     *
     * @param taskNumber Task number of Task in the TaskList.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        boolean taskNumberGreaterThanZero = taskNumber <= 0;
        boolean taskNumberMoreThanTaskListSize = taskNumber > tasks.getNumOfTasks();
        boolean isNotValidTaskNumber = taskNumberGreaterThanZero
                || taskNumberMoreThanTaskListSize;

        if (isNotValidTaskNumber) {
            throw new DukeException("Task does not exist/invalid task number.");
        }

        Task t = tasks.retrieve(taskNumber);

        if (t.isDone()) {
            throw new DukeException("Task is already marked as done.");
        }

        t.markAsDone();
        ui.doneTaskMessage(t);

        assert t.isDone();
    }
}
