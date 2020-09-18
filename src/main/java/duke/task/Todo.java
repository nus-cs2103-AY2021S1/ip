package duke.task;

public class Todo extends Task {
    private String identity;

    /**
     * Constructor for Todo class
     * @param description
     */
    public Todo(String description) {
        super(description);
        this.identity = "T";
    }

    /**
     * Getter method to retrieve type of task from instance
     * @return identity String
     */
    @Override
    public String getIdentity() {
        return identity;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}