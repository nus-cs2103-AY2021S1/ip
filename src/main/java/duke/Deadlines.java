package duke;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDateTime deadlineTime;

    public Deadlines(String taskTitle, String deadlineTime, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.TASK_TYPE_DEADLINE);
        this.deadlineTime = LocalDateTime.parse(deadlineTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static void loadDeadlineTask(String taskTitle, String deadlineTime, Boolean isDone, ArrayList<Task> tasks) {
        deadlineTime = deadlineTime.replace('T', ' ');
        Deadlines deadline = new Deadlines(taskTitle, deadlineTime, isDone);
        tasks.add(deadline);
    }

    public String printTime() {
        return deadlineTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + printTime() + ")";
    }

    @Override
    public String writeToFile() {
        return super.writeToFile() + " | " + deadlineTime;
    }

}
