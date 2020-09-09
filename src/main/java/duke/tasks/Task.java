package duke.tasks;

//Adapted from CS2103T website's duke.Tasks.Task class

/**
 * Represents a Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    public Task (String description, boolean isDone){
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Returns the StatusIcon of the Task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2714" : "\u2718");
    }

    /**
     * Marks the task as done
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Returns the description of the Task
     * @return String returns the descriptions of the Task
     */
    public String getTask() {
        return this.description;
    }

    /**
     * Returns the Status of the Task
     * @return boolean, the status of the task
     */
    public boolean getStatus(){
        return isDone;
    }

    /**
     * Returns the original Command of the Task
     * @return String the original command of the task
     */
    public String getOriginal(){
        return "task " + getTask();
    }


    public String toString(){
        return "[" + getStatusIcon() + "] " + getTask();
    }
}
