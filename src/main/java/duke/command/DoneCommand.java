package duke.command;

import duke.exception.InvalidTaskIndexException;
import duke.exception.TaskDoneException;
import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;

/**
 * Represents a Done Command by the user.
 * Apart from the parent's implementation,
 * it contains a <code>Integer</code> index of the <code>DukeTask</code>
 * to be marked done from the <code>TaskList</code>.
 */
public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public boolean getExitStatus() {
        return false;
    }

    /**
     * Marks user's <code>DukeTask</code> in the <code>TaskList</code> as done and prints feedback.
     * Location of the <code>DukeTask</code> is extracted from the given index,
     * and input validation is performed before the command is executed.
     * If the task is a form of GUI command, sets response to the result instead.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @param isGuiTask      <code>boolean</code> object to denote GUI task
     * @throws InvalidTaskIndexException If index of the task is invalid.
     * @throws TaskDoneException         If Task is completed.
     */
    @Override
    public void execute(TaskList taskList, CommandInteractionUi uiManager,
                        StorageManager storageManager, boolean isGuiTask)
            throws InvalidTaskIndexException, TaskDoneException {
        assert uiManager != null : "DoneCommand must have a uiManager";
        assert taskList != null : "DoneCommand must have a taskList";
        if (index < 0 || index >= taskList.getSize()) {
            throw new InvalidTaskIndexException();
        }
        if (taskList.getTaskList().get(index).getDoneStatus()) {
            throw new TaskDoneException();
        }
        taskList.markDone(index);
        if (isGuiTask) {
            response = uiManager.getTaskMarkAsDone(taskList.getTaskList().get(index), taskList.getSize());
        } else {
            uiManager.printTaskMarkAsDone(taskList.getTaskList().get(index), taskList.getSize());
        }
    }
}
