import java.util.ArrayList;

public class Deadlines extends Task {
    private String deadlineTime;

    public Deadlines(String taskTitle, String deadlineTime) {
        super(taskTitle, TaskTypes.DEADLINE);
        this.deadlineTime = deadlineTime;
    }

    public static void addDeadlineTask(String taskTitle, String deadlineTime, ArrayList<Task> tasks) {
        Deadlines newDeadline = new Deadlines(taskTitle, deadlineTime);
        tasks.add(newDeadline);
        Feedbacks.addDeadlineTaskMsg(tasks);
    }

    @Override
    public String toString() {
        return super.toString() + " (by:" + deadlineTime + ")";
    }
}
