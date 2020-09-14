package duke.command;

import duke.exception.InvalidTaskIndexException;
import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.task.DukeTask;

/**
 * Represents a Delete Command by the user.
 * Apart from the parent's implementation,
 * it contains a <code>Integer</code> index of the <code>DukeTask</code>
 * to be deleted from the <code>TaskList</code>.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public boolean getExitStatus() {
        return false;
    }

    /**
     * Deletes user's <code>DukeTask</code> from the <code>TaskList</code> and prints feedback.
     * Location of the <code>DukeTask</code> is extracted from the given index,
     * and input validation is performed before the command is executed.
     * If the task is a form of GUI command, sets response to the result instead.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @param isGuiTask      <code>boolean</code> object to denote GUI task
     * @throws InvalidTaskIndexException If user input validation fails.
     */
    @Override
    public void execute(TaskList taskList, CommandInteractionUi uiManager,
                        StorageManager storageManager, boolean isGuiTask)
            throws InvalidTaskIndexException {
        assert uiManager != null : "DeleteCommand must have a uiManager";
        assert taskList != null : "DeleteCommand must have a taskList";
        if (index < 0 || index >= taskList.getSize()) {
            throw new InvalidTaskIndexException();
        }
        DukeTask task = taskList.deleteFromList(index);
        if (isGuiTask) {
            this.response = uiManager.getDeleteTask(task, taskList.getSize());
        } else {
            uiManager.printDeleteTask(task, taskList.getSize());
        }
    }
}
