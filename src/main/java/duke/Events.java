package duke;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDateTime eventTime;

    public Events(String taskTitle, String eventTime, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.TASK_TYPE_EVENT);
        this.eventTime = LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static void loadEventTask(String taskTitle, String eventTime, Boolean isDone, ArrayList<Task> tasks) {
        eventTime = eventTime.replace('T', ' ');
        Events event = new Events(taskTitle, eventTime, isDone);
        tasks.add(event);
    }

    public String printTime() {
        return eventTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + printTime() + ")";
    }

    @Override
    public String writeToFile() {
        return super.writeToFile() + " | " + eventTime;
    }
}
