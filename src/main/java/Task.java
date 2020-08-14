public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method gets the status icon of the task
     * @return String of status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * This method marks the task as done.
     * @return true value
     */
    public boolean markAsDone() {
        isDone = true;
        System.out.println(this);
        return true;
    }


    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}