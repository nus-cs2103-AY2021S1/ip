package duke.task;

/**
 * Todo class define rules for todo object. Todo is a subclass of Task class.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class Todo extends Task {

    /**
     * Class constructor.
     * Initialise todo class with description and isDone.
     *
     * @param description description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Check if two object are equal.
     * If equal true, else false.
     *
     * @param o object to be compare.
     * @return true is same, false if different
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * Summary to the todo object.
     *
     * @return string to the todo object.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
