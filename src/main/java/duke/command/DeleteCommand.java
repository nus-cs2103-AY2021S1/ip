package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DeleteWrongFormatException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.Task;

import java.io.IOException;

/**
 * Encapsulates a delete command for the Duke program. This is the command that removes tasks from the task list. The
 * format for this command is: "delete x" where x is in the index of a task in the task list.
 */
public class DeleteCommand extends Command {

    /** The user's full command split into strings separated by whitespaces */
    private String[] commandParts;

    /**
     * Creates and initializes a DeleteCommand object.
     *
     * @param commandParts The user's full command split into strings separated by whitespaces.
     */
    public DeleteCommand(String[] commandParts) {
        this.commandParts = commandParts;
    }

    /**
     * Executes the command. If successful, it will remove a task from the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     * @throws DeleteWrongFormatException If the delete command is in a wrong format.
     * @throws TaskIndexOutOfBoundsException If the index of the task specified by the user is not present in the task
     * list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeleteWrongFormatException,
            TaskIndexOutOfBoundsException {
        try {
            if (commandParts.length != 2) { // If command is in a wrong format
                throw new DeleteWrongFormatException();
            }
            int taskIndex = Integer.parseInt(commandParts[1]) - 1; // Index of task in the task list
            Task removedTask = tasks.removeTask(taskIndex);
            ui.showReplyForDeleteTask(removedTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (NumberFormatException e) { // Second argument of
            // command was not a number, e.g. "delete X"
            throw new DeleteWrongFormatException();
        } catch (IndexOutOfBoundsException e) { // User requests
            // for a task with an index not within the current
            // task list
            throw new TaskIndexOutOfBoundsException(commandParts[1]);
        }
    }
}
