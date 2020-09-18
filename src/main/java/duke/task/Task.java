package duke.task;

/**
 * Represents a task which Todo, Deadline and Event extend from.
 */
public class Task {
    protected String taskName;
    protected boolean done;
    protected String taskType;

    /**
     * Constructs a new Task object.
     * @param taskName name of the Task.
     * @param taskType type of Task (Todo, Deadline or Event).
     */
    public Task(String taskName, String taskType) {
        this.taskName = taskName;
        this.done = false;
        this.taskType = taskType;
    }

    /**
     * Sets a task as done.
     */
    public void setDone() {
        this.done = true;
    }


    /**
     * Gets the name of the task.
     * @return a string representing the name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Checks if a task is done.
     * @return true if the task is done and false if it isn't.
     */
    public boolean checkDone() {
        return this.done;
    }

    /**
     * Checks if a task contains a keyword.
     * @param keyword The keyword it is checking for.
     * @return true if the task contains the keyword and false if it doesn't.
     */
    public boolean containsKeyword(String keyword) {
        if (this.taskName.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String check;
        if (done == true) {
            check = "✓";
        } else {
            check = "✗";
        }
        return "[" + taskType + "][" + check + "] " + taskName;
    }
}
