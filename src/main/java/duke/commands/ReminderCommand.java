package duke.commands;

import static duke.util.FormatChecker.checkReminderFormat;
import static duke.util.IntegerChecker.isNumber;

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
     * Creates an ReminderCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
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
     * Remind the user about upcoming deadlines.
     *
     * @param command Representing the target task and its reminder option.
     * @param tasks Object contains the task list.
     * @return A string message confirm the task to be reminded
     */
    private String setReminder(String command, TaskList tasks) {
        String[] result = command.split(" ");
        assert result[1].equals("y") || result[1].equals("n");
        assert isNumber(result[0]);
        Task task = tasks.get(Integer.parseInt(result[0]) - 1);
        return task.setReminder(result[1].equals("y"));
    }

}
