package duke.command;

import duke.exception.*;

import duke.task.TaskList;
import duke.task.Task;

import duke.Ui;
import duke.Storage;


/**
 * Represents a command to delete a task from the user's list of tasks.
 */
public class DeleteTaskCommand extends Command {

    private final String[] parsedCommand;

    /**
     * Creates and initialises a new DeleteTaskCommand object.
     *
     * @param parsedCommand String array that contains information of the task to be deleted.
     */
    public DeleteTaskCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Deletes the task from the user's list of tasks and updates the list of tasks
     * stored in the designated file.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @throws DukeException If the task cannot be deleted due to invalid arguments.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(this.parsedCommand[1]);
            if (index > tasks.getListSize()) {
                String err = "Invalid Task! The task does not exist. "
                        + "Input 'list' to view the correct task ID of your desired task.";
                throw new InvalidTaskException(err);
            } else if (index <= 0) {
                String err = "The task ID you provided is not valid. "
                        + "Input 'list' to view the correct task ID of your desired task.";
                throw new InvalidFunctionException(err);
            } else {
                Task toRemove = tasks.getTask(index - 1);
                tasks.removeTask(index - 1);
                String successReply = "Found it! This task has been successfully deleted: \n\t"
                        + toRemove.toString() + "\nYou have " + tasks.getListSize()
                        + " tasks in your list now.";
                ui.printReply(successReply);
                storage.saveFile(tasks);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No Task ID provided! Please input the ID of the task you wish to delete.";
            throw new InvalidFunctionException(err);
        } catch (NumberFormatException ex) {
            String err = "Your input is not a recognised command. You have to provide the ID of "
                    + "the task you wish to delete. \n"
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
