public class Todo extends Task {

    // Constructor

    /**
     * Creates a new Todo.
     * @param description description of Todo.
     * @throws EmptyBodyException If description given is empty.
     */
    public Todo(String description) throws EmptyBodyException {
        super(description);
    }

    // String Representation
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
