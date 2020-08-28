package duke.Tasks;

//Adapted from CS2103T website's duke.Tasks.Task class

/**
 * A task object that does the following
 * returns the status of the task
 * returns the task
 * returns the original command that made the task
 * marks the task as done, changes the status of the task
 */
public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Creates a Task object that is not done
     * @param description of the task
     */
    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task Object and can specify the status of the task
     * @param description
     * @param isDone
     */
    public Task (String description, boolean isDone){
        this.isDone = isDone;
        this.description = description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Changes the Status of Task to done
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Returns the description of the Task
     * @return Task description
     */
    public String getTask() {
        return this.description;
    }

    /**
     * Returns the status of the Task
     * @return status of the Task
     */
    public boolean getStatus(){
        return isDone;
    }

    /**
     * Returns the original command used to create the Task
     * @return command used to create the Task
     */
    public String getOriginal(){
        return "task " + getTask();
    }

    /**
     * Returns the Task with the StatusIcon
     * @return Task with the StatusIcon
     */
    public String toString(){
        return "[" + getStatusIcon() + "] " + getTask();
    }
}
