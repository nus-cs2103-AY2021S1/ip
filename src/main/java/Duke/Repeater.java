package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import task.Task;

/**
 * Repeater is the class that contains the logic for events and deadlines to be recurring.
 *
 * @author Joshua
 */
public class Repeater {

    /**
     * SAVE_READ_DATETIME_FORMAT is the date time format that dates are stored in Duke.
     */
    private static final DateTimeFormatter SAVE_READ_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Converts the time given in the task to another time if the task has already expired. For example, if the task
     * is set to recur daily, the expired task will be set to the same time it is supposed to occur the following
     * day.
     * @param task the task that is recurring.
     * @return the new time that the task should be occuring at.
     */
    public static LocalDateTime correctDate(Task task) {
        LocalDateTime taskTime = task.getTime();
        LocalDateTime currentTime = LocalDateTime.now();
        while (taskTime.isBefore(currentTime)) {
            switch (task.getFrequency().toString()) {
            case "daily":
                taskTime = taskTime.plusDays(1);
                break;
            case "weekly":
                taskTime = taskTime.plusDays(7);
                break;
            case "monthly":
                taskTime = taskTime.plusMonths(1);
                break;
            default:
                assert false;
            }
        }
        return taskTime;
    }

    /**
     * Sets the task to recur based on the given settings stored with the task.
     *
     * @param task the task that is recurring.
     * @param recurrenceFormat the settings saved for the recurring task.
     */
    public static void setRecurrence(Task task, String recurrenceFormat) {
        switch (recurrenceFormat) {
        case "[DRP]":
            task.setRepeated(Parser.FrequencyOfRecurrence.daily);
            break;
        case "[WRP]":
            task.setRepeated(Parser.FrequencyOfRecurrence.weekly);
            break;
        case "[MRP]":
            task.setRepeated(Parser.FrequencyOfRecurrence.monthly);
            break;
        default:
            assert false;
        }
    }
}
