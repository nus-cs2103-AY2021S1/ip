package duke.task;

public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    /**
     * @return a string indicating the details of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
