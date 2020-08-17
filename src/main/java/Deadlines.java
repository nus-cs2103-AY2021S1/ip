import java.util.ArrayList;

public class Deadlines extends Task {
    private String deadlineTime;

    public Deadlines(String taskTitle, String deadlineTime) {
        super(taskTitle);
        this.deadlineTime = deadlineTime;
    }

    public static void createDeadline(String taskTitle, String deadlineTime, ArrayList<Task> tasks) {
        Deadlines newDeadline = new Deadlines(taskTitle, deadlineTime);
        tasks.add(newDeadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + deadlineTime + ")";
    }
}
