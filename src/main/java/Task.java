public class Task {
    protected String desc;
    protected boolean isDone;

    /**
     * Initialises a Task that is by default not done.
     *
     * @param desc Description of Task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Produces the check or cross mark depending on isDone.
     *
     * @return String that contains either check or cross mark.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Sets isDone to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.desc;
    }

}
