package duke;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles Event tasks.
 */
public class Events extends Task {
    protected LocalDateTime eventTime;

    /**
     * Events class constructor.
     *
     * @param taskTitle A string of Event task name.
     * @param eventTime A string of Event time.
     * @param isDone Status of the Event task.
     */
    public Events(String taskTitle, String eventTime, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.TASK_TYPE_EVENT);
        this.eventTime = LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Loads Event tasks from the file at beginning.
     *
     * @param taskTitle A string of Event task name.
     * @param eventTime A string of Event time.
     * @param isDone Status of the Event task.
     * @param tasks The overall user's task list.
     */
    public static void loadEventTask(String taskTitle, String eventTime, Boolean isDone, ArrayList<Task> tasks) {
        eventTime = eventTime.replace('T', ' ');
        Events event = new Events(taskTitle, eventTime, isDone);
        tasks.add(event);
    }

    /**
     * Prints the Event task time in the format of "d MMM yyyy hh:mm am/pm".
     *
     * @return A string of the Event task time in the format of "d MMM yyyy hh:mm am/pm".
     */
    public String printTime() {
        return eventTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
    }

    /**
     * Returns a string of Event task.
     *
     * @return A string of Event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + printTime() + ")";
    }

    /**
     * Returns a string follows the format of the file.
     *
     * @return A string follows the format of the file.
     */
    @Override
    public String writeToFile() {
        return super.writeToFile() + " | " + eventTime;
    }
}
