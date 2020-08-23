package duke.commands;

import duke.tasks.Task;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Represents a Done command for when the user wants to mark
 * a task as done.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public final int sizeOffset = -1;

    /**
     * Creates an instance of a Done Command with the appropriate
     * task to be set as done as the command description.
     *
     * @param commandDescription
     */
    public DoneCommand(String commandDescription) {
        super(commandDescription, false);
    }

    /**
     * Executes the command.
     * Marks the appropriate task as done.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Task task = taskList.getTaskAtIndex(Integer.parseInt(commandDescription) + sizeOffset);
        task.setDone();
        ui.displayDoneTask(task);
    }
}
