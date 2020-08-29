package duke.commands;

import static java.lang.Integer.parseInt;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;



/**
 * Class that simulates the done command.
 */

public class DoneCommand extends Command {
    private static final String INVALID_INPUT = "Invalid input for done";
    /**
     * Creates a DoneCommand object.
     * 
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public DoneCommand(String[] inputArr) {
        super(inputArr);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return marking(parseInt(inputArr[1]), ui, tasks);
    }

    /**
     * Mark the task at that index 'pos' of the list. If the index 'pos' less than of equals to 0 or greater than the 
     * size of the list, a message will printed, notifying the user of the invalid input.
     * 
     * @param pos Index of the task to be marked in the list.
     * @param ui Object that deals with interactions with the user.
     * @param tasks Object contains the task list.
     */
    private String marking(int pos, Ui ui, TaskList tasks) {
        if (pos <= 0 || tasks.size() < pos) {
            return ui.messageFormatter(new String[]{INVALID_INPUT});
        } else {
            Task task = tasks.get(pos - 1);
            return ui.messageFormatter(new String[]{task.markAsDone()});
        }
    }
}
