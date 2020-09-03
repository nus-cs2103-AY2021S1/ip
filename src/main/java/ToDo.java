public class ToDo extends Task {
    /**
     * Constructs new Todo object.
     *
     * @param description Description of the Todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
