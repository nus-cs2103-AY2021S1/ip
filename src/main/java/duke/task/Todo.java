package duke.task;


public class Todo extends Task {

    /**
     * Create a todo item with description.
     *
     * @param description Description of the todo item.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a todo item with status and description.
     *
     * @param isDone      Status of the todo item.
     * @param description Descrption of the todo item.
     */
    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toFileStringFormat() {
        return String.format("T / %d / %s", isDone ? 1 : 0, this.desciption);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
