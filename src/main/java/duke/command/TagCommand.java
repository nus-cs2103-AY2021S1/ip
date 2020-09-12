package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;
import duke.exception.InvalidTaskException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to attach a tag to a task.
 */
public class TagCommand extends Command {

    /** Parsed commands containing details of the tag and the task to be tagged. */
    private final String[] parsedCommand;

    /**
     * Creates and initialises a new TagCommand object.
     *
     * @param parsedCommand String array that contains information of the tag and the task to be tagged.
     */
    public TagCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Attaches a tag to a task and updates it accordingly in the user's list of tasks
     * stored in the designated file.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply for successful tagging of the desired task.
     * @throws DukeException If the tag could not be attached to the task due to invalid arguments.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskID = Integer.parseInt(this.parsedCommand[0]);
            if (taskID > tasks.getListSize() || taskID <= 0) {
                String error = "Invalid Task! The task ID you provided is not valid. ";
                throw new InvalidTaskException(error);
            }
            String tag = this.parsedCommand[1];
            assert !tag.isBlank() : "tag cannot be empty";
            Task task = tasks.getTask(taskID - 1);
            task.setTaskTag(tag);
            storage.saveToFile(tasks);
            return ui.showAddedTag(task);
        } catch (NumberFormatException ex) {
            String error = "Your tag command has an incorrect format.";
            throw new InvalidFunctionException(error);
        }
    }

    /**
     * Indicates if the Duke session has ended.
     *
     * @return False since the Duke session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
