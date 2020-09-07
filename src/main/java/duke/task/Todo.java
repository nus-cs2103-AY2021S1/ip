package duke.task;

/**
 * Encapsulates the idea of tasks without any date/time attached,
 * hence inherits from Task
 */
public class Todo extends Task {

    // constants
    private static final String TYPE = "T";

    // constructors
    public Todo(String desc) {
        super(desc);
    }
    public Todo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    // accessors
    public String getLabel() {
        return TYPE;
    }

    // String representation methods
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", TYPE, getStateLabel(), desc);
    }
    @Override
    public String databaseRep() {
        return TYPE + " | " + super.databaseRep();
    }

}
