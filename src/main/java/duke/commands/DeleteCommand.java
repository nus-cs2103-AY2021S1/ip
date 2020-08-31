package duke.commands;

import static java.lang.Integer.parseInt;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;



/**
 * Class the simulates the delete command.
 */

public class DeleteCommand extends Command {
    private static final String INVALID_INPUT = "Invalid input for delete";
    private static final String DELETE_NOTIFICATION = "Noted. I've removed this task:";
    /**
     * Creates a DeleteCommand object.
     * 
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public DeleteCommand(String[] inputArr) {
        super(inputArr);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return deleteTask(parseInt(inputArr[1]), ui, tasks);
    }

    /**
     * Deletes the task at that index in the list. If the index 'pos' less than of equals to 0 or greater than the size
     * of the list, a message will printed, notifying the user of the invalid input.
     * 
     * @param pos Index of the task to be deleted in the list.
     * @param ui Object that deals with interactions with the user.
     * @param tasks Object contains the task list.
     */
    private String deleteTask(int pos, Ui ui, TaskList tasks) {
        if (pos <= 0 || pos > tasks.size()) {
            return ui.messageFormatter(INVALID_INPUT);
        } else {
            Task task = tasks.get(pos - 1);
            tasks.remove(pos - 1);
            return ui.messageFormatter(DELETE_NOTIFICATION, task.toString(),printNumTask(tasks));
        }
    }
}
