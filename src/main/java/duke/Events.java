package duke;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class Events extends Task {
    protected LocalDateTime eventTime;

    /**
     *
     * @param taskTitle
     * @param eventTime
     * @param isDone
     */
    public Events(String taskTitle, String eventTime, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.EVENT);
        this.eventTime = LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     *
     * @param taskTitle
     * @param eventTime
     * @param isDone
     * @param tasks
     */
    public static void loadEventTask(String taskTitle, String eventTime, Boolean isDone, ArrayList<Task> tasks) {
        eventTime=eventTime.replace('T',' ');
        Events event = new Events(taskTitle, eventTime, isDone);
        tasks.add(event);
    }

    /**
     *
     * @return
     */
    public String printTime() {
        return eventTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + printTime() + ")";
    }

    /**
     *
     * @return
     */
    @Override
    public String writeToFile() {
        return super.writeToFile() + " | " + eventTime;
    }
}
