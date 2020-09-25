package duke.commands;

import static duke.util.FormatChecker.checkDoneFormat;
import static duke.util.IntegerChecker.isNumber;
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
     * InitializeCreates a DoneCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     * Index 0 contains the type of command while Index 1 contains the message of the command.
     */
    public DoneCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFormatDoneException {
        checkDoneFormat(tasks, inputArr);
        return mark(parseInt(inputArr[1]), ui, tasks);
    }

    /**
     * Mark the task at that index 'pos' of the list. If the index 'pos' is less than or equals to 0 or greater than
     * the size of the list, a message will printed, notifying the user of the invalid input.
     *
     * @param pos Index of the task to be marked in the list.
     * @param ui Object that deals with interactions with the user.
     * @param tasks Object contains the task list.
     * @return If index is valid, a String message will be displayed notifying which task has been marked, else there
     * will be a message notifying about the invalid input.
     */
    private String mark(int pos, Ui ui, TaskList tasks) {
        assert isNumber(Integer.toString(pos)) : "pos is not a number";
        Task task = tasks.get(pos - 1);
        return ui.messageFormatter(task.markAsDone());
    }
}
