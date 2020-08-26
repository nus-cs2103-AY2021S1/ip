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
     * A function to set a task as done.
     */
    public void setDone() {
        this.done = true;
    }


    /**
     * A function to get the name of the task.
     * @return a string representing the name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * A function to check if a task is done.
     * @return true if the task is done and false if it isn't.
     */
    public boolean checkDone() {
        return this.done;
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