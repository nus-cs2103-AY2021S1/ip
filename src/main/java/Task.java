/**
 * Task are work that has a name (that's a string) and a boolean which is true when a task is completed.
 *
 * @author Dominic Siew Zhen Yu
 */
public class Task {
    private String name;
    private boolean completed;

    private String COMPLETED = "[✓]";
    private String UNCOMPLETED = "[✕]";

    /**
     * the constructor of the Task class.
     *
     * @param name the name of the task
     */

    public Task(String name) {
        this.name = name;
        completed = false;
    }

    /**
     * printName() method returns the name of the Task object, and whether the task is completed or not.
     *
     * @return a string representation of a Task object
     */

    public String printName() {
        String completionStatus = completed? COMPLETED: UNCOMPLETED;
        return completionStatus + " " + this.name;
    }

    /**
     * the toggleComplete() method toggles the boolean "completed" when the task is completed,
     * or if the user wishes to indicate that a task is incompleted if it is indicated as
     * otherwise.
     *
     */

    public void toggleComplete() {
        completed = !completed;
    }
}
