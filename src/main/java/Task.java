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

    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    /**
     * Changes the state of this task, isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Overrides the toString method
     *
     * @return a custom event description
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }

}