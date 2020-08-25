package tasks;
public class ToDo extends Task {

    /**
     * Returns a ToDo task
     *
     * @param description description of todo.
     * @see Task
     */
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
