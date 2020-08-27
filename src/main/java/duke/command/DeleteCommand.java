package duke.command;

import duke.exception.InvalidTaskIndexException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;
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
        super(false);
        this.index = index;
    }

    /**
     * Deletes user's <code>DukeTask</code> from the <code>TaskList</code> and prints feedback.
     * Location of the <code>DukeTask</code> is extracted from the given index,
     * and input validation is performed before the command is executed.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @throws InvalidTaskIndexException If user input validation fails.
     */
    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager)
            throws InvalidTaskIndexException {

        if (index < 0 || index >= taskList.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            DukeTask task = taskList.deleteFromList(index);
            uiManager.printDeleteTask(task, taskList.getSize());
        }
    }
}
