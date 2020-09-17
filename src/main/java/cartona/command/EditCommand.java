package cartona.command;

import cartona.Storage;
import cartona.exception.CartonaException;
import cartona.task.Task;
import cartona.task.TaskList;
import cartona.ui.Ui;

/**
 * The EditCommand class, when executed, runs the steps required to edit a specific Task within the TaskList.
 *
 * @author Jaya Rengam
 */
public class EditCommand implements Command {
    private static EditParser editParser = new EditParser();

    private boolean hasExecuted;

    private int taskIdToEdit;
    /** The String from the user containing information about the task to be edited */
    private String editInstruction;

    EditCommand(int taskIdToEdit, String editString) {
        this.hasExecuted = false;
        this.taskIdToEdit = taskIdToEdit;
        this.editInstruction = editString;
    }

    /**
     * Edits the Task given by taskIdToComplete in the TaskList and updates the Storage text file.
     *
     * @param taskList the TaskList being modified
     * @param ui the Ui object that is used to print the action to the console
     * @param storage the Storage object used to update the text file
     * @throws CartonaException if the command has already been executed
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        // Check if the command has already been executed.
        if (hasExecuted) {
            throw new CartonaException("Error: DeleteCommand already executed");
        }

        // Edit the Task
        Task replacementTask = editParser.parseReplacement(taskList, taskIdToEdit, editInstruction);
        taskList.editTask(taskIdToEdit, replacementTask);

        // Update Storage
        storage.saveListToFile(taskList);

        this.hasExecuted = true;

        // Return UI message
        return ui.printTaskEditMessage(taskIdToEdit, replacementTask);
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
