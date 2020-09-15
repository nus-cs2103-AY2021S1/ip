package duke.command;

import java.util.Queue;

import duke.action.Action;
import duke.action.UpdateAction;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a call to update a Task.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";
    private final int taskNumber;

    /**
     * Constructor for DoneCommand.
     *
     * @param taskNumber Task number of Task in the TaskList.
     */
    public UpdateCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui,
                        Storage storage,
                        TaskList tasks,
                        Queue<Action> actionQueue) throws DukeException {

        boolean taskNumberGreaterThanZero = taskNumber <= 0;
        boolean taskNumberMoreThanTaskListSize = taskNumber > tasks.getNumOfTasks();
        boolean isNotValidTaskNumber = taskNumberGreaterThanZero
                || taskNumberMoreThanTaskListSize;

        if (isNotValidTaskNumber) {
            throw new DukeException("Task does not exist/invalid task number.");
        }

        Action a = new UpdateAction(ui, tasks, taskNumber);
        a.prompt(ui);
        actionQueue.add(a);
    }
}
