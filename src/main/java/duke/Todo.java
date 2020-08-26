package duke;

/**
 * The Todo class represents tasks that do not have a specified time.
 */
public class Todo extends Task {

    /**
     * Constructor which takes in a description of the task name.
     *
     * @param description name of the task
     */
    public Todo(String description) {
        super(description);
        symbol = "[T]";
    }

    @Override
    public String toString() {
        return (getSymbol() + super.toString());
    }
}
