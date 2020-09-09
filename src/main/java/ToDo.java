public class ToDo extends Task {

    /**
     * Initialises a Todo.
     *
     * @param desc Description of Todo.
     */
    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
