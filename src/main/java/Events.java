import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDateTime eventTime;

    public Events(String taskTitle, String eventTime) {
        super(taskTitle, TaskTypes.EVENT);
        this.eventTime = LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public static void addEventTask(String taskTitle, String eventTime, ArrayList<Task> tasks) {
        Events newEvent = new Events(taskTitle, eventTime);
        tasks.add(newEvent);
        Feedbacks.addEventTaskMsg(tasks);
    }

    public String printTime() {
        return eventTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + printTime() + ")";
    }
}
