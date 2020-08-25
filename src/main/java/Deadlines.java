import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDateTime deadlineTime;

    public Deadlines(String taskTitle, String deadlineTime) {
        super(taskTitle, TaskTypes.DEADLINE);
        this.deadlineTime = LocalDateTime.parse(deadlineTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public static void addDeadlineTask(String taskTitle, String deadlineTime, ArrayList<Task> tasks) {
        Deadlines newDeadline = new Deadlines(taskTitle, deadlineTime);
        tasks.add(newDeadline);
        Feedbacks.addDeadlineTaskMsg(tasks);
    }

    public String printTime() {
        return deadlineTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
    }
    @Override
    public String toString() {
        return super.toString() + " (by: " + printTime() + ")";
    }
}
