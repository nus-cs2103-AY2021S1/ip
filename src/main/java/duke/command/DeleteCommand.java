package duke.command;

import duke.Storage;
import duke.Ui;

import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;
import duke.exception.InvalidTaskException;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to delete a task from the user's list of tasks.
 */
public class DeleteCommand extends Command {

    /** Parsed commands containing details of the task to be deleted. */
    private final String[] parsedCommand;

    /**
     * Creates and initialises a new DeleteCommand object.
     *
     * @param parsedCommand String array that contains information of the task to be deleted.
     */
    public DeleteCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Deletes the task from the user's list of tasks and updates the task list
     * stored in the designated file.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply for successful deletion of task.
     * @throws DukeException If the task cannot be deleted due to invalid arguments.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(this.parsedCommand[1]);
            if (index > tasks.getListSize() || index <= 0) {
                String err = "Invalid Task! The task ID you provided is not valid. ";
                throw new InvalidTaskException(err);
            } else {
                Task toRemove = tasks.getTask(index - 1);
                tasks.removeTask(index - 1);
                storage.saveToFile(tasks);
                return ui.printDeleteTask(toRemove, tasks.getListSize());
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No Task ID provided! Please input the ID of the task you wish to delete.";
            throw new InvalidFunctionException(err);
        } catch (NumberFormatException ex) {
            String err = "Your input is not a recognised command. You have to provide the ID of "
                    + "the task you wish to delete.";
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
