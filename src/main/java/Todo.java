/**
 * Class representing tasks to be done
 */
public class Todo extends Task {

    /**
     * Constructor of Todo class.
     *
     * @param description Todo description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
