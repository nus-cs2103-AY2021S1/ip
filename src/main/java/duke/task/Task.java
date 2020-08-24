package duke.task;

/**
 * Encapsulate the task class
 */
public class Task {
    protected String description;
    protected boolean done = false;

    /**
     * Creates a new task
     * @param description Description of the task
     */
    public Task(String description){
        this.description = description;

    }

    /**
     * Returns task description
     * @return Description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as complete
     */
    public void completeTask() {
        done = true;
    }

    /**
     * Returns the string representation of the task
     * @return String representation of the task
     */
    @Override
    public String toString() {

        return (this.done ? "[✓]":"[✘]")+" "+this.description;
    }

    /**
     * Returns the string to be saved in a file
     * @return String to be saved
     */
    public String toFileString() {
        StringBuilder str = new StringBuilder();
        str.append(this.done ? "T" :"F");
        str.append("\n");
        str.append(this.description);
        str.append("\n");
        return str.toString();
    }
}
