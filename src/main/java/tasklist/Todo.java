package tasklist;

/**
 * Todo class is a subclass of Task.
 * Todo stores each Todo's description.
 * @author Maguire Ong
 */

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
