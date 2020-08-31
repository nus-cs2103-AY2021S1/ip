package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Represents a delete command for when the user wants to
 * delete a specific task.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public final int sizeOffset = -1;

    /**
     * Creates an instance of a DeleteCommand with the appropriate
     * task to be deleted as the command description.
     * @param commandDescription Task to be deleted.
     */
    public DeleteCommand(String commandDescription) {
        super(commandDescription, false);
    }

    /**
     * Executes the command and returns Duke's response.
     * Deletes the appropriate task from the task list.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     * @return String of Duke response.
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        Task task = taskList.getTaskAtIndex(Integer.parseInt(commandDescription) + sizeOffset);
        taskList.removeFromList(Integer.parseInt(commandDescription) + sizeOffset);
        storage.saveData(taskList, ui);
        return ui.displayDeletedTask(task, taskList.getListSize());
    }
}
