package duke.command;

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
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     * @throws EmptyTimeException If deadline portion of task is empty.
     * @throws InvalidEventException If input does not follow the format of {@code Event} tasks.
     * @throws InvalidDeadlineException Not thrown here in this method.
     * @throws FileUpdateFailException If file in storage fails to get updated.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTimeException, InvalidEventException,
        InvalidDeadlineException, FileUpdateFailException {
        String[] resultArr = TaskParser.parseTaskDescription(description, TaskType.EVENT);
        String taskDetails = resultArr[0];
        String timeFrame = resultArr[1];
        return addTask(new Event(taskDetails, timeFrame), tasks, ui, storage);
    }
}
