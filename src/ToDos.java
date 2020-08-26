/**
 * ToDos class that represents a Task to be done. Extends from the Task class.
 */
public class ToDos extends Task {
    /**
     * Overloaded constructor that creates a todo object that has a description of the
     * @param description a String representing the description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Constructor that creates a todo object that has a description of it.
     * and whether the task has been completed.
     * @param description a String representing the description of the task.
     * @param isDone a boolean representing whether the task has been completed.
     */
    public ToDos(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", "[T]", this.getIcon(), description);
    }

    @Override
    public String toSaveString() {
        return String.format("T | %s | %s", super.doneString(), this.description);
    }

}
