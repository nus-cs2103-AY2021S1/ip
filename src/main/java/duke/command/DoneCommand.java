package duke.command;

import duke.Storage;
import duke.Ui;

import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;
import duke.exception.InvalidTaskException;

import duke.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {

    /** Parsed commands containing details of the task to be marked as done. */
    private final String[] parsedCommand;

    /**
     * Creates and initialises a new DoneCommand object.
     *
     * @param parsedCommand String array that contains information of the task to be marked as done.
     */
    public DoneCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Marks the task as done and updates it accordingly in the user's list of tasks
     * stored in the designated file.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @throws DukeException If the task cannot be mark completed due to invalid arguments.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(this.parsedCommand[1]);
            if (index > tasks.getListSize() || index <= 0) {
                String err = "Invalid Task! The task ID you provided is not valid. "
                        + "Input 'list' to view the correct task ID of your desired task.";
                throw new InvalidTaskException(err);
            } else {
                tasks.completeTask(index - 1);
                storage.saveToFile(tasks);
                return ui.printDoneTask(tasks.getTask(index - 1));
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No Task ID provided! Please input the ID of the task you wish to mark as completed.";
            throw new InvalidFunctionException(err);
        } catch (NumberFormatException ex) {
            String err = "Your input is not a recognised command. You have to provide the ID of "
                    + "the task you wish to mark as done. \n"
                    + "Input '/commands' to view a list of my commands. ";
            throw new InvalidFunctionException(err);
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
