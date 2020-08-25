import java.util.ArrayList;

public class Events extends Task {
    private String eventTime;

    public Events(String taskTitle, String eventTime, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.EVENT);
        this.eventTime = eventTime;
    }

    public static void addNewEventTask(String taskTitle, String eventTime, ArrayList<Task> tasks) {
        Events newEvent = new Events(taskTitle, eventTime, false);
        tasks.add(newEvent);
        Feedbacks.addEventTaskMsg(tasks);
    }

    public static void loadEventTask(String taskTitle, String eventTime, Boolean isDone, ArrayList<Task> tasks) {
        Events event = new Events(taskTitle, eventTime, isDone);
        tasks.add(event);
    }

    @Override
    public String toString() {
        return super.toString() + " (at:" + eventTime + ")";
    }

    @Override
    public String writeToFile() {
        return super.writeToFile() + " | " + eventTime;
    }
}
