public class Deadline extends Task {
    protected String dueDateTime;

    Deadline(String description, int id, String dueDateTime) {
        super(description, id);
        this.dueDateTime = dueDateTime;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " +
                this.description + "(" + this.dueDateTime + ")";
    }
}
