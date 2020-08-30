package duke.tasks;

/**
 * <code>duke.tasks.Task</code> is the base class for all kinds of duke.tasks.
 */
public class Task {
    private boolean isCompleted;
    private String name;

    /**
     * Constructor for creating a task.
     * @param name the name of this task object
     */
    Task(String name) {
        // TODO: initialise the isCompleted field
        this.name = name;
    }

    /**
     * Gets the name of this task.
     * @return the name of this class as a <code>String</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a boolean value indicating whether or not this task is completed.
     * @return <code>true</code> if this task is completed, <code>false</code> otherwise
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Returns a comma seperated <code>String</code> containing the relevant information
     * about this task. This information will then be saved in a text file.
     * @return the comma seperated text of this task
     */
    public String saveText() {
        String completeStatus = isCompleted ? "1" : "0";
        return "duke.tasks.Task," +  completeStatus + "," + name;
    }

    /**
     * Marks this task as done by setting the value of its field
     * <code>isCompleted</code> to <code>true</code>.
     */
    public void setCompleted() {
        isCompleted = true;
    }

    /**
     * <code>String</code> represenation of this task object.
     * @return the string representation this object
     */
    @Override
    public String toString() {
        String symbol = isCompleted ? "\u2713" : "x";
        return "[" + symbol + "] " + name; 
    }
}
