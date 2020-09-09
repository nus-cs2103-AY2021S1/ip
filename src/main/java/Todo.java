/**
 * Todo is a task without any date/time attached to it e.g., visit new theme park
 */
public class Todo extends Task {
    /**
     * Creates a todo with the string name that has not been done
     * @param name
     */
    Todo(String name) {
        this(name, false);
    }

    /**
     * Creates a todo with the name and isCompleted
     * @param name
     * @param isCompleted
     */
    Todo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
}
