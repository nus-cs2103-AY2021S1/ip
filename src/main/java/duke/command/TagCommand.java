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

    /** Parsed commands containing details of the tag and the task to tagged. */
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
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply for successful tagging of the desired task.
     * @throws DukeException If the tag cannot be attached to the task due to invalid arguments.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] tagInfo = retrieveTagInfo();
        int taskID = Integer.parseInt(tagInfo[0]);
        String tag = tagInfo[1];
        if (taskID > tasks.getListSize() || taskID <= 0) {
            String error = "Invalid Task! The task ID you provided is not valid. ";
            throw new InvalidTaskException(error);
        }
        Task task = tasks.getTask(taskID - 1);
        task.setTaskTag(tag);
        storage.saveToFile(tasks);
        return ui.showAddedTag(task);
    }

    /**
     * Retrieves the details of the tag command and stores it in an array.
     *
     * @return String array containing the ID of the task to be tagged and the tag.
     * @throws InvalidFunctionException If the user input has missing arguments.
     */
    private String[] retrieveTagInfo() throws InvalidFunctionException {
        try {
            String[] commandArguments = this.parsedCommand[1].trim().split(" ", 2);
            int taskID = Integer.parseInt(commandArguments[0]);
            String tag = commandArguments[1].trim();
            return new String[]{Integer.toString(taskID), tag};
        } catch (ArrayIndexOutOfBoundsException ex) {
            String error = "Your tag command has missing arguments";
            throw new InvalidFunctionException(error);
        } catch (NumberFormatException ex) {
            String error = "No task ID provided! Please input the ID of the task you wish to tag.";
            throw new InvalidFunctionException(error);
        }
    }


    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
