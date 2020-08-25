package duke;
/**
 * A type of task which contains description
 */
public class Todo extends Task{
    /**
     * constructor of Todo
     * @param description description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * represents Todo in string
     * @return string representation of Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
