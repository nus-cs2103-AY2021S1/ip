package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.util.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that marks the designated task as complete and broadcasts the appropriate update.
 */
public class DoneCommand extends Command {

    /** Default message to be sent when a Task is marked as done */
    protected static final String DONE_MESSAGE = "Nice! I've marked this task as done: ";

    /** Index of task to be marked as complete */
    private int doneIndex;

    /** Private constructor which is not able to handle String inputs directly. */
    private DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    /**
     * Static factory method for creating the appropriate done command from a String input.
     * @param command String input of the form "done {index}" (index starts at 1)
     * @return command object that marks the task as done when executed
     */
    public static DoneCommand parse(String command) {
        String[] details = command.split(" ", 2);
        if (details.length == 1) {
            throw new DukeException("Please specify a task to complete!");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(details[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number input!");
        }
        return new DoneCommand(taskNumber);
    }

    /**
     * Marks the index of the designated task as done.
     * The method also broadcasts an update through the UI and updates the storage file.
     * @param taskList List of Tasks to work with
     * @param ui UI element to be used
     * @param storage Storage element to be used
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (doneIndex > taskList.size()) {
            throw new DukeException("No such task!");
        }
        Task doneTask = taskList.get(doneIndex);
        doneTask.markDone();
        ui.outputMessage(createDoneMessage(doneTask));
        storage.updateFile(taskList);
    }


    /**
     * Standard String creator for the update.
     * Edit this to adjust the message sent when the command is executed.
     * @param taskDone task marked as done
     * @return formatted String notifying of the update
     */
    private String createDoneMessage(Task taskDone) {
        return DONE_MESSAGE + "\n   " + taskDone;
    }






}
