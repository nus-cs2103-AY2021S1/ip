public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String name, String details) {
        super(name);
        this.dueDate = details;
    }

    // Gets deadline of the task
    public String getDeadline() {
        return this.dueDate;
    }

    @Override
    public String toString() {
        // By default print task name
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
