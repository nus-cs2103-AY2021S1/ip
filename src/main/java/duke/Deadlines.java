package duke;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class Deadlines extends Task {
    private LocalDateTime deadlineTime;

    /**
     *
     * @param taskTitle
     * @param deadlineTime
     * @param isDone
     */
    public Deadlines(String taskTitle, String deadlineTime, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.DEADLINE);
        this.deadlineTime = LocalDateTime.parse(deadlineTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     *
     * @param taskTitle
     * @param deadlineTime
     * @param isDone
     * @param tasks
     */
    public static void loadDeadlineTask(String taskTitle, String deadlineTime, Boolean isDone, ArrayList<Task> tasks) {
        deadlineTime=deadlineTime.replace('T',' ');
        Deadlines deadline = new Deadlines(taskTitle, deadlineTime, isDone);
        tasks.add(deadline);
    }

    /**
     *
     * @return
     */
    public String printTime() {
        return deadlineTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + printTime() + ")";
    }

    /**
     * 
     * @return
     */
    @Override
    public String writeToFile() {
        return super.writeToFile() + " | " + deadlineTime;
    }

}
