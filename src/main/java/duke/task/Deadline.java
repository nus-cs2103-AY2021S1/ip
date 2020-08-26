package duke.task;

/**
 * Represents a specific task which has a deadline.
 */
public class Deadline extends Task {

    protected LocalDate date = null;
    protected String by = null;

    public Deadline(String description, String by) {
        super(description);
        tag = "D";
        String[] bySplit = by.split(" ", 2);
        date = LocalDate.parse(bySplit[0]);
        if (bySplit.length > 1) {
            this.by = bySplit[1];
        }
    }

    public Deadline(String description) {
        super(description);
        tag = "D";
    }

    @Override
    public String getTaskType() {
        return tag;
    }

    public String toPrint(){
        return super.toPrint() + "|" + date + "|" + by;
    }

    @Override
    public String toString() {
        return by == null
                ? "[D]" + super.toString()
                : "[D]" + super.toString() + " (by: " + by + ")";
    }
}