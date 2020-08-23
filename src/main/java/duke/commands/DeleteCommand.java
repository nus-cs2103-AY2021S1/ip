package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.storage.Storage;
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
     * Executes the command.
     * Deletes the appropriate task from the task list.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Task task = taskList.getTaskAtIndex(Integer.parseInt(commandDescription) + sizeOffset);
        taskList.removeFromList(Integer.parseInt(commandDescription) + sizeOffset);
        ui.displayDeletedTask(task, taskList.getListSize());
    }
}
