public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Creates a task
     *
     * @param description the content of the class
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }


    protected String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }


    /**
     * Changes the state of this task, isDone to true
     */
    public void markAsDone() {
        isDone = true;
    }


    /**
     * Overrides the toString method
     *
     * @return a custom event description
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }


}