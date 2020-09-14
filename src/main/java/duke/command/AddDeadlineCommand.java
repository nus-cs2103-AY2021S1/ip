package duke.command;

import java.time.LocalDateTime;

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
 * AddDeadlineCommand class abstracts the execution of adding {@code Deadline} tasks.
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
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     * @throws EmptyTimeException If deadline portion of task is empty.
     * @throws InvalidEventException Not thrown in this method.
     * @throws InvalidDeadlineException If input does not follow the format of {@code Deadline} tasks.
     * @throws InvalidDateException If date of task does not follow the format.
     * @throws FileUpdateFailException If file in storage fails to get updated.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTimeException, InvalidEventException,
        InvalidDeadlineException, InvalidDateException, FileUpdateFailException {

        String[] parsedArr = TaskParser.parseTaskDescription(description, TaskType.DEADLINE);
        String deadline = getDeadline(parsedArr);
        String taskDetails = getTaskDetails(parsedArr);
        LocalDateTime dateTime = DateTimeParser.getDateTime(deadline);
        return addTask(new Deadline(taskDetails, dateTime), tasks, ui, storage);
    }

    /**
     * Retrieves the task details from the parsed input.
     *
     * @param arr Parsed array.
     * @return Details of Deadline task.
     */
    private String getTaskDetails(String[] arr) {
        return arr[0];
    }

    /**
     * Retrieves the deadline of this {@code Deadline} task.
     *
     * @param arr Parsed array.
     * @return Deadline of Deadline task.
     */
    private String getDeadline(String[] arr) {
        return arr[1];
    }
}
