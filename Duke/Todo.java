package Duke;

/**
 * The Todo class that represents a Todo task.
 *
 * @author Zeng Yu Ting
 * @version 1.0
 * @since 2020-15-08
 */
public class Todo extends Task {
    public Todo(String description) {
        this.description = description;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
