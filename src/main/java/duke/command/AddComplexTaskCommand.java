package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.ComplexTask;
import duke.task.TaskType;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Adds a complex task into the task list.
 */
public class AddComplexTaskCommand extends AddCommand {

    private static final String DATE_TIME_FORMAT = "MMM d yyyy / h.mm a";
    private static final String DATE_FORMAT = "MMM d yyyy";
    private static final String TIME_FORMAT = "h.mm a";

    private final TaskType taskType;
    private final String description;

    /**
     * Initialises the AddComplexTaskCommand object with the task details and task type.
     *
     * @param description Task description.
     * @param taskType Type of Task.
     */
    public AddComplexTaskCommand(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
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
        String[] inputArr = Parser.parseComplexTaskDescription(description, taskType);
        String taskDetails = inputArr[0];
        String date = inputArr[1];
        ComplexTask complexTask;
        if (isDateAndTimeFormat(date)) {
            complexTask = new ComplexTask(taskDetails, taskType, dateAndTimeToString(date));
        } else if (isDateFormat(date)) {
            complexTask = new ComplexTask(taskDetails, taskType, dateToString(date));
        } else if (isTimeFormat(date)) {
            complexTask = new ComplexTask(taskDetails, taskType, timeToString(date));
        } else {
            complexTask = new ComplexTask(taskDetails, taskType, date);
        }
        return addTask(complexTask, tasks, ui, storage);
    }

    /**
     * Checks if the input given is in a LocalDateTime format.
     *
     * @param input User input.
     * @return True if input is in a LocalDateTime format, false otherwise.
     */
    private boolean isDateAndTimeFormat(String input) {
        try {
            LocalDateTime.parse(matchDateTimeFormat(input));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Formats the date and time to a fixed MMM d yyyy / h.mm a format.
     *
     * @param dateAndTime Input date and time from user.
     * @return Formatted date and time.
     */
    private String dateAndTimeToString(String dateAndTime) {
        return LocalDateTime
            .parse(matchDateTimeFormat(dateAndTime))
            .format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    /**
     * Tries to match the input to the date and time format.
     *
     * @param input Input String.
     * @return Formatted String.
     */
    private String matchDateTimeFormat(String input) {
        return input.replace(" ", "T");
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
     * Formats the date to a fixed MMM d YYYY format.
     *
     * @param date Input date from user.
     * @return Formatted date.
     */
    private String dateToString(String date) {
        return LocalDate.parse(date).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
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

    /**
     * Formats the time to a fixed h.mm a format.
     *
     * @param time Input time from user.
     * @return Formatted time.
     */
    private String timeToString(String time) {
        return LocalTime.parse(time).format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }
}
