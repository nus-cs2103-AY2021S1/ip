/**
 * Represents a to-do task without any specific time attached.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.type = "T";
    }
    /**
     * Constructor for a to-do.
     * @param description Description of the to-do.
     * @param isDone Boolean value indicating if the to-do is completed.
     * @return nothing
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone, "T");
    }

    @Override
    public String toString() {
        return super.toString() + (this.tag == null ? "" : (" <" + this.tag + ">"));
    }

}