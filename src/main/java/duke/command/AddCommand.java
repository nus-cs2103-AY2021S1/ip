package duke.command;

import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.task.DukeTask;

/**
 * Represents an Add Command by the user.
 * Apart from the parent's implementation,
 * it contains a <code>DukeTask</code> task to be added to the <code>TaskList</code>.
 */
public class AddCommand extends Command {
    private final DukeTask task;

    public AddCommand(DukeTask task) {
        super();
        this.task = task;
    }

    @Override
    public boolean getExitStatus() {
        return false;
    }

    /**
     * Adds user's <code>DukeTask</code> to the <code>TaskList</code> and prints feedback.
     * If the task is a form of GUI command, sets response to the result instead.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @param isGuiTask      <code>boolean</code> object to denote GUI task
     */
    @Override
    public void execute(TaskList taskList, CommandInteractionUi uiManager,
                        StorageManager storageManager, boolean isGuiTask) {
        taskList.addToList(task);
        if (isGuiTask) {
            this.response = uiManager.getAddTask(task, taskList.getSize());
        } else {
            uiManager.printAddTask(task, taskList.getSize());
        }
    }
}
