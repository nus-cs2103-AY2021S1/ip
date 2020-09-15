package duke.task;

/**
 * Handles instances of todos, a subclass of Task with no date but only description
 *
 */
public class Todo extends Task {

    private String identifier;

    /**
     * Constructor for todo
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
        this.identifier = "T";
    }

    /**
     * Getter for identifier to help write tasks to duke.txt
     *
     * @return
     */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }
}