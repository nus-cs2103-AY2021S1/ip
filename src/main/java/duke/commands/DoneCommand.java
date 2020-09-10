package duke.commands;

import static duke.util.FormatChecker.checkDoneFormat;
import static duke.util.Keyword.KEYWORD_DONE_INVALID_INPUT;
import static java.lang.Integer.parseInt;

import duke.exception.InvalidFormatDoneException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

/**
 * Class that simulates the done command.
 */

public class DoneCommand extends Command {
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
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFormatDoneException {
        checkDoneFormat(inputArr);
        return marking(parseInt(inputArr[1]), ui, tasks);
    }

    /**
     * Marks the task at that index 'pos' of the list. If the index 'pos' is less than or equals to 0 or greater than
     * the size of the list, a message will printed, notifying the user of the invalid input.
     *
     * @param pos Index of the task to be marked in the list.
     * @param ui Object that deals with interactions with the user.
     * @param tasks Object contains the task list.
     * @return A String message that this particular task is marked or has been marked before.
     */
    private String marking(int pos, Ui ui, TaskList tasks) {
        if (checkInValidIndex(pos, tasks)) {
            return ui.messageFormatter(KEYWORD_DONE_INVALID_INPUT);
        } else {
            Task task = tasks.get(pos - 1);
            return ui.messageFormatter(task.markAsDone());
        }
    }

    private boolean checkInValidIndex(int pos, TaskList tasks) {
        return pos <= 0 || pos > tasks.size();
    }
}
