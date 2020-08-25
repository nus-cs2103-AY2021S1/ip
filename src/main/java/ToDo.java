/**
 * Defines a ToDo type task.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
