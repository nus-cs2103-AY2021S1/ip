package duke.command;

import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;
import duke.task.DukeTask;

/**
 * Represents an Add Command by the user.
 * Apart from the parent's implementation,
 * it contains a <code>DukeTask</code> task to be added to the <code>TaskList</code>.
 */
public class AddCommand extends Command {
    private final DukeTask task;

    public AddCommand(DukeTask task) {
        super(false);
        this.task = task;
    }

    /**
     * Adds user's <code>DukeTask</code> to the <code>TaskList</code> and prints feedback.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     */
    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager) {
        taskList.addToList(task);
        uiManager.printAddTask(task, taskList.getSize());
    }
}
