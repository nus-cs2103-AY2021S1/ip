package duke;

public class ToDo extends Task {
    protected String at;

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string that represents the todo task
     *
     * @return String of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
