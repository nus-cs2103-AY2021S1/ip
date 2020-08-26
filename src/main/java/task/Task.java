package task;

/**
 * Task consists of the details of the item the user wants to complete and
 * a boolean which indicates whether the task is completed or not
 *
 * @author (Sruthi)
 */
public class Task {
    private final String item;
    private boolean isCompleted;

    /**
     * Task takes in a String which contains the details of the task
     * and a boolean which indicates whether the task is completed.
     *
     * @param item description of the task
     * @param isCompleted whether the task is completed
     */
    Task(String item, boolean isCompleted) {
        this.item = item;
        this.isCompleted = isCompleted;
    }

    /**
     * Sets the task to completed(true).
     */
    public void completeTask() {
        this.isCompleted = true;
    }

    /**
     * It returns the String to be printed to the user containing
     * the details of the task.
     *
     * @return String to be printed to the user
     */
    public String getItem() {
        if (isCompleted) {
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
