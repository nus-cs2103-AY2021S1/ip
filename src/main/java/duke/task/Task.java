package duke.task;

import java.time.LocalDate;

/**
 * Task Class consists of methods related to Task.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String time;
    protected LocalDate localDate;

    /**
     * Constructs a task.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructs a task with time as an additional argument.
     *
     * @param taskName Name of the task.
     * @param time Time of the task.
     */
    public Task(String taskName, String time) {
        this.taskName = taskName;
        this.isDone = false;
        this.time = time;
        try {
            this.localDate = LocalDate.parse(time.trim());
        } catch (Exception e) {
            localDate = null;
        }
    }

    /**
     * Sets the task to be done.
     */
    public void setTaskToBeDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representing the status of the task.
     *
     * @return Symbol of the status of the task.
     */
    public String getStatusSymbol() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /**
     * Sets the task status.
     *
     * @param whetherIsDone Boolean showing whether the task is done.
     */
    public void setWhetherTaskDone(String whetherIsDone) {
         isDone = whetherIsDone.equals("true");
    }

    /**
     * Returns a string of the format required by the storage file.
     *
     * @return String describing the task.
     */
    public String writeToFile() {
        return this.getStatusSymbol() + "|" + this.taskName;
    }

    /**
     * Returns a string of a format to be printed by Duke.
     *
     * @return String describing the task.
     */
    @Override
    public String toString() {
        return "[" +
                this.getStatusSymbol() + "] " + taskName;
    }
}
