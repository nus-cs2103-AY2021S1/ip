package duke;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles deadline tasks
 */
public class Deadlines extends Task {
    private LocalDateTime deadlineTime;

    /**
     * Deadlines class constructor
     *
     * @param taskTitle A string of deadline task name
     * @param deadlineTime A string of deadline time
     * @param isDone Status of the deadline task
     */
    public Deadlines(String taskTitle, String deadlineTime, Boolean isDone) {
        super(taskTitle, isDone, TaskTypes.TASK_TYPE_DEADLINE);
        this.deadlineTime = LocalDateTime.parse(deadlineTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Loads deadline tasks from the file at beginning
     *
     * @param taskTitle A string of deadline task name
     * @param deadlineTime A string of deadline time
     * @param isDone Status of the deadline task
     * @param tasks The overall user's task list
     */
    public static void loadDeadlineTask(String taskTitle, String deadlineTime, Boolean isDone, ArrayList<Task> tasks) {
        deadlineTime = deadlineTime.replace('T', ' ');
        Deadlines deadline = new Deadlines(taskTitle, deadlineTime, isDone);
        tasks.add(deadline);
    }

    /**
     * Prints the deadline task time in the format of "d MMM yyyy hh:mm am/pm"
     *
     * @return A string of the deadline task time in the format of "d MMM yyyy hh:mm am/pm"
     */
    public String printTime() {
        return deadlineTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
    }

    /**
     * Returns a string of deadline task
     *
     * @return A string of deadline task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + printTime() + ")";
    }

    /**
     * Returns a string follows the format of the file
     * @return A string follows the format of the file
     */
    @Override
    public String writeToFile() {
        return super.writeToFile() + " | " + deadlineTime;
    }

}
