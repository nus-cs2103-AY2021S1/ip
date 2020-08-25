package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DoneWrongFormatException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.Task;

import java.io.IOException;

/**
 * Encapsulates a done command for the Duke program. This is the command that marks tasks as completed in the task list.
 * The format for this command is: "done x" where x is in the index of a task in the task list.
 */
public class DoneCommand extends Command {

    /** The user's full command split into strings separated by whitespaces */
    private String[] commandParts;

    /**
     * Creates and initializes a DoneCommand object.
     *
     * @param commandParts The user's full command split into strings separated by whitespaces.
     */
    public DoneCommand(String[] commandParts) {
        this.commandParts = commandParts;
    }

    /**
     * Executes the command. If successful, it will mark a task as done in the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     * @throws DoneWrongFormatException If the done command is in a wrong format.
     * @throws TaskIndexOutOfBoundsException If the index of the task specified by the user is not present in the task
     * list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoneWrongFormatException,
            TaskIndexOutOfBoundsException {
        try {
            if (commandParts.length != 2) { // If command is in a wrong format
                throw new DoneWrongFormatException();
            }
            int taskIndex = Integer.parseInt(commandParts[1]) - 1; // Index of task in the task list
            Task completedTask = tasks.getTask(taskIndex);
            completedTask.setDone(true);
            ui.showReplyForDoneTask(completedTask);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (NumberFormatException e) { // Second argument of command was not a number, e.g. "done X"
            throw new DoneWrongFormatException();
        } catch (IndexOutOfBoundsException e) { // User requests for a task with an index not within the current task
            // list
            throw new TaskIndexOutOfBoundsException(commandParts[1]);
        }
    }
}
