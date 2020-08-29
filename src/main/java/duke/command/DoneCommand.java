package duke.command;

import duke.exception.InvalidTaskIndexException;
import duke.exception.TaskDoneException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UiManager;

/**
 * Represents a Done Command by the user.
 * Apart from the parent's implementation,
 * it contains a <code>Integer</code> index of the <code>DukeTask</code>
 * to be marked done from the <code>TaskList</code>.
 */
public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Marks user's <code>DukeTask</code> in the <code>TaskList</code> as done and prints feedback.
     * Location of the <code>DukeTask</code> is extracted from the given index,
     * and input validation is performed before the command is executed.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @throws InvalidTaskIndexException If index of the task is invalid.
     * @throws TaskDoneException         If Task is completed.
     */
    @Override
    public void execute(TaskList taskList, UiManager uiManager, StorageManager storageManager)
            throws InvalidTaskIndexException, TaskDoneException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new InvalidTaskIndexException();
        } else if (taskList.getTaskList().get(index).getDoneStatus()) {
            throw new TaskDoneException();
        } else {
            taskList.markDone(index);
            uiManager.printMarkAsDone(taskList.getTaskList().get(index), taskList.getSize());
        }
    }
}
