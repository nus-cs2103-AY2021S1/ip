package junimo.task;

/**
 * Represents a Todo object which inherits from Task.
 */
public class Todo extends Task {

    /**
     * Constructs an instance of Todo object.
     * @param description Description of task.
     * @param isDone Whether task is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Implements method specified by abstract class Task.
     * @return String of Todo object in format for saving to and retrieving from hard disk.
     */
    public String getParsedTask() {
        return "todo " + description + System.lineSeparator()
                + isDone + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Overrides Object equals method.
     * @param other Object compared to.
     * @return True if other is also a Todo object with the same description and isDone fields. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Todo) {
            Todo otherTodo = (Todo) other;
            return description.equals(otherTodo.description) && (isDone == otherTodo.isDone);
        } else {
            return false;
        }
    }
}
