package duke.commands;

import static duke.util.FormatChecker.checkDeleteFormat;
import static duke.util.Keyword.KEYWORD_DELETE_INVALID_INPUT;
import static duke.util.Keyword.KEYWORD_DELETE_NOTIFICATION;
import static java.lang.Integer.parseInt;

import duke.exception.InvalidFormatDeleteException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;


/**
 * Class that simulates the delete command.
 */
public class DeleteCommand extends Command {
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFormatDeleteException {
        checkDeleteFormat(inputArr);
        return deleteTask(parseInt(inputArr[1]), ui, tasks);
    }

    /**
     * Deletes the task at that index in the list. If the index 'pos' less than of equals to 0 or greater than the size
     * of the list, a message will printed, notifying the user of the invalid input.
     *
     * @param pos Index of the task to be deleted in the list.
     * @param ui Object that deals with interactions with the user.
     * @param tasks Object contains the task list.
     * @return A string message notifying which task has been deleted
     */
    private String deleteTask(int pos, Ui ui, TaskList tasks) {
        if (checkInValidIndex(pos, tasks)) {
            return ui.messageFormatter(KEYWORD_DELETE_INVALID_INPUT);
        } else {
            Task task = tasks.get(pos - 1);
            tasks.remove(pos - 1);
            return ui.messageFormatter(KEYWORD_DELETE_NOTIFICATION, task.toString(), printNumTask(tasks));
        }
    }
    private boolean checkInValidIndex(int pos, TaskList tasks) {
        return pos <= 0 || pos > tasks.size();
    }
}
