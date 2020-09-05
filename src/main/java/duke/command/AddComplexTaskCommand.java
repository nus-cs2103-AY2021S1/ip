package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.EmptyTimeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.storage.Storage;
import duke.task.ComplexTask;
import duke.task.TaskType;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Adds a complex task into the task list.
 */
public class AddComplexTaskCommand extends AddCommand {

    private final TaskType taskType;

    /**
     * Initialises the AddComplexTaskCommand object with the task details and task type.
     *
     * @param taskDetails Task details.
     * @param taskType Type of Task.
     */
    public AddComplexTaskCommand(String taskDetails, TaskType taskType) {
        super(taskDetails);
        this.taskType = taskType;
    }

    /**
     * Returns the unique identifier tied to the ComplexTask.
     *
     * @return String identifier of the Task.
     */
    private String identifier() {
        assert (taskType == TaskType.DEADLINE || taskType == TaskType.EVENT);
        return taskType == TaskType.DEADLINE ? " /by" : " /at";
    }

    /**
     * Adds a complex task into the TaskList Object.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @throws DukeException If input format is wrong.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputArr = getTaskDetails().split(identifier(), 2);
        if (inputArr.length == 1) {
            if (taskType == TaskType.DEADLINE) {
                throw new InvalidDeadlineException();
            } else {
                throw new InvalidEventException();
            }
        }
        checkIfEmpty(inputArr);
        String date = inputArr[1].trim();
        ComplexTask complexTask;
        if (isDateAndTimeFormat(date.replace(" ", "T"))) {
            complexTask = new ComplexTask(inputArr[0], taskType, dateAndTimeToString(date));
        } else if (isDateFormat(date)) {
            complexTask = new ComplexTask(inputArr[0], taskType, dateToString(date));
        } else if (isTimeFormat(date)) {
            complexTask = new ComplexTask(inputArr[0], taskType, timeToString(date));
        } else {
            complexTask = new ComplexTask(inputArr[0], taskType, date);
        }
        return addTask(complexTask, tasks, ui, storage);
    }

    /**
     * Checks if the description or time section from user input is empty.
     *
     * @param inputArr Input array.
     * @throws DukeException If the description or time section is empty.
     */
    private void checkIfEmpty(String[] inputArr) throws DukeException {
        String description = inputArr[0];
        String time = inputArr[1];
        if (description.isEmpty()) {
            throw new EmptyTaskException(taskType);
        }
        if (time.isBlank()) {
            throw new EmptyTimeException(taskType);
        }
    }

    /**
     * Formats the date to a fixed MMM d YYYY format.
     *
     * @param date Input date from user.
     * @return Formatted date.
     */
    private String dateToString(String date) {
        return LocalDate.parse(date).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Formats the date and time to a fixed MMM d yyyy / h.mm a format.
     *
     * @param dateAndTime Input date and time from user.
     * @return Formatted date and time.
     */
    private String dateAndTimeToString(String dateAndTime) {
        return LocalDateTime
            .parse(dateAndTime.replace(" ", "T"))
            .format(DateTimeFormatter.ofPattern("MMM d yyyy / h.mm a"));
    }

    /**
     * Formats the time to a fixed h.mm a format.
     *
     * @param time Input time from user.
     * @return Formatted time.
     */
    private String timeToString(String time) {
        return LocalTime.parse(time).format(DateTimeFormatter.ofPattern("h.mm a"));
    }

    /**
     * Checks if the input given is in a LocalDate format.
     *
     * @param input User input.
     * @return True if input is in a LocalDate format, false otherwise.
     */
    private boolean isDateFormat(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if the input given is in a LocalDateTime format.
     *
     * @param input User input.
     * @return True if input is in a LocalDateTime format, false otherwise.
     */
    private boolean isDateAndTimeFormat(String input) {
        try {
            LocalDateTime.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if the input given is in a LocalTime format.
     *
     * @param input User input.
     * @return True if input is in a LocalTime format, false otherwise.
     */
    private boolean isTimeFormat(String input) {
        try {
            LocalTime.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
