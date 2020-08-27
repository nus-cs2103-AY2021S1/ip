/**
 * Represents a Task that the user has. A Task object has a task name, which
 * describes the task, as well as a status to indicate whether the task
 * has been completed. The class also keeps track of the total number of
 * tasks the user has in the list so far.
 */
public class Task {
    private String taskName;
    private boolean isDone;
    
    private static String TICK = "\u2713";
    private static String CROSS = "\u2718";
    
    public static int totalTasks = 0;

    /**
     * Instantiates a Task object.
     *
     * @param taskName The description of the task.
     */
    public Task(String taskName) {
        totalTasks++;
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task in the format to be saved in the computer.
     *
     * @return String representation of the task.
     */
    public String toTaskData() {
        return (this.isDone ? "1" : "0") + " ; " + this.taskName;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return Returns the string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? TICK : CROSS) +
                "] " + taskName;
    }
}
