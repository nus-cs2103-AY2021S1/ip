package duke.command;

import java.time.LocalDateTime;

import duke.exception.DuplicateTaskException;
import duke.exception.EmptyTimeException;
import duke.exception.FileUpdateFailException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.parser.DateTimeParser;
import duke.parser.TaskParser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskType;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * AddDeadlineCommand class abstracts the execution of adding {@code Deadline} a task.
 */
public class AddDeadlineCommand extends AddCommand {

    private final String description;

    /**
     * Initializes the {@code AddDeadlineCommand} object.
     *
     * @param description Description of deadline task.
     */
    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a {@code Deadline} task into the {@code TaskList}.
     *
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     * @throws EmptyTimeException If deadline portion of task is empty.
     * @throws InvalidEventException Not thrown in this method.
     * @throws InvalidDeadlineException If input does not follow the format of a {@code Deadline} task.
     * @throws InvalidDateException If date of task does not follow the format.
     * @throws FileUpdateFailException If file in storage fails to get updated.
     * @throws DuplicateTaskException If the new task to be added already exists in the current {@code TaskList}.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyTimeException, InvalidEventException,
            InvalidDeadlineException, InvalidDateException, FileUpdateFailException, DuplicateTaskException {

        String[] parsedArr = TaskParser.parseTaskDescription(description, TaskType.DEADLINE);
        String taskDetails = TaskParser.getTaskDetails(parsedArr);
        String deadline = TaskParser.getTime(parsedArr);
        LocalDateTime dateTime = DateTimeParser.getDateTime(deadline);
        return addTask(new Deadline(taskDetails, dateTime), taskList, ui, storage);
    }
}
