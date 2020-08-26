package task;

/**
 * Task consists of the details of the item the user wants to complete and
 * a boolean which indicates whether the task is completed or not
 *
 * @author (Sruthi)
 */
public class Task {
    private String item;
    private boolean completed;

    /**
     * Task takes in a String which contains the details of the task
     * and a boolean which indicates whether the task is completed.
     *
     * @param item description of the task
     * @param completed whether the task is completed
     */
    Task(String item, boolean completed) {
        this.item = item;
        this.completed = completed;
    }

    /**
     * Sets the task to completed(true).
     */
    public void completeTask() {
        this.completed = true;
    }

    /**
     * It returns the String to be printed to the user containing
     * the details of the task.
     *
     * @return String to be printed to the user
     */
    public String getItem() {
        if (completed) {
            return "[O]" + this.item;
        } else {
            return "[X]" + this.item;
        }
    }

    /**
     * It returns the String inputted by the user
     *
     * @return String to be printed to the user
     */
    public String getInput() {
        return this.item;
    }
}
