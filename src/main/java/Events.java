import java.util.ArrayList;

public class Events extends Task {
    private String eventTime;

    public Events(String taskTitle, String eventTime) {
        super(taskTitle);
        this.eventTime = eventTime;
    }

    public static void addEventTask(String taskTitle, String eventTime, ArrayList<Task> tasks) {
        Events newEvent = new Events(taskTitle, eventTime);
        tasks.add(newEvent);
        Feedbacks.addEventTaskMsg(tasks);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + eventTime + ")";
    }
}
