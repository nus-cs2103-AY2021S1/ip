package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns unicode symbols (tick or cross) corresponding to the completion state of the task.
     * @return Tick if the task is complete, cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as complete.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns an array of Strings representing the state of the task, to be passed to Storage to 
     * be formatted and written to a file.
     * @return Array of Strings representing the current state of the Task.
     */
    public String[] serialize() {
        String[] output = new String[3];
        output[0] = this.isDone
                    ? "1"
                    : "0";
        output[1] = "Task";
        output[2] = description;
        
        return output;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
