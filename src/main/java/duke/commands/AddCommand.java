package duke.commands;

import static duke.util.DateFormatter.formatDateTime;
import static duke.util.FormatChecker.checkDeadlineFormat;
import static duke.util.FormatChecker.checkEmptyText;
import static duke.util.FormatChecker.checkEventFormat;
import static duke.util.Keyword.KEYWORD_ADD_NOTIFICATION;
import static duke.util.Keyword.KEYWORD_DEADLINE;
import static duke.util.Keyword.KEYWORD_DEADLINE_FORMAT;
import static duke.util.Keyword.KEYWORD_EVENT;
import static duke.util.Keyword.KEYWORD_EVENT_FORMAT;
import static duke.util.Keyword.KEYWORD_TODO;

import duke.exception.DuplicateException;
import duke.exception.EmptyTextException;
import duke.exception.InvalidFormatDateException;
import duke.exception.InvalidFormatDeadlineException;
import duke.exception.InvalidFormatEventException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

/**
 * Class that simulates the add command of the user.
 * Mainly: event, deadline, todo.
 */
public class AddCommand extends Command {
    /**
     * Creates an AddCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public AddCommand(String[] inputArr) {
        super(inputArr);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFormatDeadlineException,
            InvalidFormatEventException, InvalidFormatDateException, DuplicateException, EmptyTextException {
        checkEmptyText(inputArr);
        return addTask(inputArr[0], inputArr[1], ui, tasks);
    }

    /**
     * Adds the given task into the task list. Expected format for the date in message is YYYY-MM-DD HHMM or
     * YYYY-MM-DD HHMM. If type is of todo, date can be omitted.
     *
     * @param type Type of task that is being entered (todo, event, deadline).
     * @param message Details of the task that the user entered.
     * @param ui Object that deals with interactions with the user.
     * @param tasks Object contains the task list.
     * @return A string message showing the information of the task that has been added.
     * @throws InvalidFormatDeadlineException Throws an exception when the format of 'message' is wrong.
     * @throws InvalidFormatEventException Throws an exception when the format of 'message' is wrong.
     * @throws InvalidFormatDateException Throws an exception when the format of 'message' is wrong.
     * @throws DuplicateException Throws an exception when there is a duplicate task.
     */
    private String addTask(String type, String message, Ui ui, TaskList tasks) throws InvalidFormatDeadlineException,
            InvalidFormatEventException, InvalidFormatDateException, DuplicateException {
        Task task = null;
        String[] dateTime;
        switch (type) {
        case KEYWORD_TODO:
            task = new ToDo(message);
            break;
        case KEYWORD_DEADLINE:
            dateTime = message.split(KEYWORD_DEADLINE_FORMAT, 2);
            checkDeadlineFormat(dateTime);
            task = new Deadline(dateTime[0], formatDateTime(dateTime[1]));
            break;
        case KEYWORD_EVENT:
            dateTime = message.split(KEYWORD_EVENT_FORMAT, 2);
            checkEventFormat(dateTime);
            task = new Event(dateTime[0], formatDateTime(dateTime[1]));
            break;
        default:
            assert false : "Invalid task";
        }
        assert task != null;
        return addTask(tasks, task, ui);
    }

    /**
     * Check for duplicate before adding.
     *
     * @param tasks Object contains the task list.
     * @param task The task to be added.
     * @param ui Object that deals with interactions with the user.
     * @return A string representing the addition of the task.
     * @throws DuplicateException Throws an exception when there is a duplicate task.
     */
    private String addTask(TaskList tasks, Task task, Ui ui) throws DuplicateException {
        if (checkDuplicates(tasks, task)) {
            throw new DuplicateException();
        } else {
            tasks.add(task);
            return ui.messageFormatter(KEYWORD_ADD_NOTIFICATION, task.toString(), printNumTask(tasks));
        }
    }
    private boolean checkDuplicates(TaskList taskList, Task task) {
        return taskList.checkExistBefore(task);
    }
}
