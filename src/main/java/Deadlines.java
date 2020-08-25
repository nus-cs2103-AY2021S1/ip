import java.util.ArrayList;

public class Deadlines extends Task {
    private String deadlineTime;

    public Deadlines(String taskTitle, String deadlineTime, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.DEADLINE);
        this.deadlineTime = deadlineTime;
    }

    public static void addNewDeadlineTask(String taskTitle, String deadlineTime, ArrayList<Task> tasks) {
        Deadlines newDeadline = new Deadlines(taskTitle, deadlineTime, false);
        tasks.add(newDeadline);
        Feedbacks.addDeadlineTaskMsg(tasks);
    }

    public static void loadDeadlineTask(String taskTitle, String deadlineTime, Boolean isDone, ArrayList<Task> tasks) {
        Deadlines deadline = new Deadlines(taskTitle, deadlineTime, isDone);
        tasks.add(deadline);
    }

    public static String getDeadlineTime(Deadlines deadline) {
        return deadline.deadlineTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (by:" + deadlineTime + ")";
    }

    @Override
    public String writeToFile() {
        return super.writeToFile() + " | " + deadlineTime;
    }

}
