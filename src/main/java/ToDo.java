public class ToDo extends Task {

    /**
     * Initialise a todo.
     * @param desc contains description.
     */
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
