package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a Done command for when the user wants to mark
 * a task as done.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private static final int SIZE_OFFSET = -1;

    /**
     * Creates an instance of a Done Command with the appropriate
     * task to be set as done as the command description.
     *
     * @param commandDescription Description of the command body.
     */
    public DoneCommand(String commandDescription) {
        super(commandDescription, false);
    }

    /**
     * Executes the command and returns Duke's response.
     * Marks the appropriate task as done.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     * @return String of Duke response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTaskAtIndex(Integer.parseInt(commandDescription) + SIZE_OFFSET);
            task.setDone();
            storage.saveData(taskList, ui);
            return ui.displayDoneTask(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("There's no such element!");
        } catch (NumberFormatException e) {
            return ui.showError("Looks like your input was invalid! Enter --help for more information");
        }
    }
}
