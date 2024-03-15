public class Deadline extends Task {
    protected String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    public Deadline(String description, String due, Boolean isDone) {
        super(description, isDone);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + this.getIcon() + description + " (by: " + due + ")";
    }

    public String toSaveString() {
        return String.format("D | %s | %s | %s", super.doneString(), this.description, this.due);
    }

}
