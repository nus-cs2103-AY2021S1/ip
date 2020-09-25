package duke.commands;

import static duke.util.FormatChecker.checkReminderFormat;
import static duke.util.IntegerChecker.isNumber;
import static duke.util.Keyword.KEYWORD_NO;
import static duke.util.Keyword.KEYWORD_ONE_SPACE;
import static duke.util.Keyword.KEYWORD_YES;

import duke.exception.InvalidFormatReminderException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

/**
 * Class that simulates the remind command.
 */
public class ReminderCommand extends Command {

    /**
     * Initialize an ReminderCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     * Index 0 contains the type of command while Index 1 contains the message of the command.
     */
    public ReminderCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFormatReminderException {
        checkReminderFormat(tasks, inputArr);
        return setReminder(inputArr[1], tasks);
    }

    /**
     * Set a task on reminder. It will appear on the left hand corner of the GUI.
     *
     * @param command Representing the target task and its reminder option.
     * @param tasks Object contains the task list.
     * @return A string message confirm the task to be reminded
     */
    private String setReminder(String command, TaskList tasks) {
        String[] result = command.split(KEYWORD_ONE_SPACE);
        assert result[1].equals(KEYWORD_YES) || result[1].equals(KEYWORD_NO);
        assert isNumber(result[0]);
        Task task = tasks.get(Integer.parseInt(result[0]) - 1);
        return task.setReminder(result[1].equals(KEYWORD_YES));
    }
}
