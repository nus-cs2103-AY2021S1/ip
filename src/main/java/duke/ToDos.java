package duke;

public class ToDos extends Task {

    /**
     * Class Constructor specifying the description of the Task.
     * @param description
     */
    public ToDos(String description) {
        super(description);
        this.type = "ToDos";
    }

    /**
     * Returns string description of ToDos.
     * @return String description of ToDos.
     */
    public String toString() {
        return "  [" + this.getType() + "][" + this.getStatusIcon() + "] "
                + this.getDescription() + this.recurringPrinter();
    }
}
