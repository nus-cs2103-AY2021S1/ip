package duke.Tasks;

/**
 * An abstract class providing the blueprint for a task.
 */
public class Task {

    /** The description of the task. */
    private String name;

    /** Boolean to determine if the task is completed. */
    protected boolean isDone;

    /** Constructor to create a Task. */
    public Task(String name){
        this.name = name;
        isDone = false;
    }

    /**
     * Mark the task as completed.
     */
    public void completed(){
        this.isDone = true;
    }

    /**
     * Converts the task to a writable format for the data file.
     * @return The string representation of the task for the data file.
     */
    public String toData(){
        return (isDone ? "1|" : "0|") + name + "|";
    }

    /**
     * Prints the string representation for the task for the user.
     * @return The string representation for the task for the user.
     */
    @Override
    public String toString(){
        return (isDone ? "[\u2705]" : "[\u2718]") + " " + name;
    }
}
