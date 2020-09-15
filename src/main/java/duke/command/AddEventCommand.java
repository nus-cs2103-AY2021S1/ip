package duke.command;

import duke.exception.DuplicateTaskException;
import duke.exception.EmptyTimeException;
import duke.exception.FileUpdateFailException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.parser.TaskParser;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskType;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Abstracts the adding of Event task.
 */
public class AddEventCommand extends AddCommand {

    private final String description;

    /**
     * Initializes the {@code AddEventCommand} object.
     *
     * @param description Description of the Event.
     */
    public AddEventCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a {@code Event} task into the {@code TaskList}.
     *
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     * @throws EmptyTimeException If deadline portion of task is empty.
     * @throws InvalidEventException If input does not follow the format of an {@code Event} task.
     * @throws InvalidDeadlineException Not thrown here in this method.
     * @throws FileUpdateFailException If file in storage fails to get updated.
     * @throws DuplicateTaskException If the new task to be added already exists in the current {@code TaskList}.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyTimeException, InvalidEventException,
        InvalidDeadlineException, FileUpdateFailException, DuplicateTaskException {
        String[] resultArr = TaskParser.parseTaskDescription(description, TaskType.EVENT);
        String taskDetails = getTaskDetails(resultArr);
        String timeFrame = getTimeFrame(resultArr);
        return addTask(new Event(taskDetails, timeFrame), taskList, ui, storage);
    }

    /**
     * Retrieves the task details from the parsed input.
     *
     * @param arr Parsed array.
     * @return Details of Event task.
     */
    private String getTaskDetails(String[] arr) {
        return arr[0];
    }

    /**
     * Retrieves the deadline of this {@code Event} task.
     *
     * @param arr Parsed array.
     * @return Timeframe of Event task.
     */
    private String getTimeFrame(String[] arr) {
        return arr[1];
    }
}
