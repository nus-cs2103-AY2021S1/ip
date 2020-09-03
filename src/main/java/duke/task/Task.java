package duke.task;

/**
 * Represents a task which Todo, Deadline and Event extend from.
 */
public class Task {
    protected String taskName;
    protected boolean done;
    protected String taskType;

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