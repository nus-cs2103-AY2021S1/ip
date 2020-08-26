package duke.task;

/**
 * Task class that represents a task for the user
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task Class constructor. Create a new Task with task description and isDone status.
     *
     * @param description give the description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method that generate the status icon of whether or not the task is completed.
     * @return  a String of the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    /**
     * Method that mark the current task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method that get the description of the Task
     * @return  a String that describes the Task
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * To String method of Task
     * @return  a String that describes the Task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
