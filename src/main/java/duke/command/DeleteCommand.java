package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.util.TaskList;

/**
 * Command that deletes a Task (by index) from the TaskList and broadcasts the appropriate update.
 */
public class DeleteCommand extends Command {

    /** Default message to be sent when a Task is added */
    protected static final String DELETE_MESSAGE = "Noted. I've removed this task: ";

    /** Index of item to be deleted from the task list. Index starts at 1 */
    private int itemIndex;

    /** Private constructor which is not able to handle String inputs directly. */
    private DeleteCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    /**
     * Returns a DeleteCommand made from the appropriate string put.
     * This is a static factory method which handles invalid formats as well.
     *
     * @param command String input of the form "delete {index}" (index starts at 1).
     * @return command object that deletes the designated task when executed.
     * @throws DukeException if the command given is invalid, with the reason provided.
     */
    public static DeleteCommand parse(String command) throws DukeException {
        String[] details = command.split(" ");
        if (details.length == 1) {
            throw new DukeException("Please specify a task to delete!");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(details[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number input!");
        }
        return new DeleteCommand(taskNumber);
    }

    /**
     * Deletes the task corresponding to the index from the task list.
     * The method also broadcasts an update through the UI and updates the storage file.
     *
     * @param taskList List of Tasks to work with.
     * @param ui UI element to be used.
     * @param storage Storage element to be used.
     * @throws DukeException if the number provided is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        boolean isValidIndex = itemIndex <= taskList.size() && itemIndex > 0;
        if (!isValidIndex) {
            throw new DukeException("Please enter a valid number between 1 and " + taskList.size());
        }

        Task removedTask = taskList.remove(itemIndex);
        ui.outputMessage(createDeleteMessage(removedTask, taskList));
        storage.updateFile(taskList);
    }

    /**
     * Returns a formatted message for when a task is deleted.
     * Edit this to adjust the message sent when the command is executed.
     *
     * @param taskRemoved task that was removed.
     * @param taskList task list where the task was deleted from.
     * @return formatted String notifying of the update.
     */
    private String createDeleteMessage(Task taskRemoved, TaskList taskList) {
        return DELETE_MESSAGE + '\n'
                + "   " + taskRemoved + '\n'
                + taskList.createTaskNumberCountMessage();
    }


}
