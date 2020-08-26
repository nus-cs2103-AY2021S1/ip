package duke.task;

public class Todo extends Task {

    /**
     * Initializes Todo object.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Todo information.
     *
     * @return Information regarding the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Todo information in the format to be written to duke.txt.
     *
     * @return Information regarding the todo.
     */
    @Override
    public String toFileString() {
        return "T\n" + super.getDone() + "\n" + super.toFileString() + "\n\n";
    }
}