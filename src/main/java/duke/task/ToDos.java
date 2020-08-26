package duke.task;

/**
 * Represents a specific task which is a to do.
 */
public class ToDos extends Task {

    protected LocalDate date = null;
    protected String by = null;

    public ToDos(String description, String by) {
        super(description);
        tag = "T";
        String[] bySplit = by.split(" ", 2);
        date = LocalDate.parse(bySplit[0]);
        if (bySplit.length > 1) {
            this.by = bySplit[1];
        }
    }

    public ToDos(String description) {
        super(description);
        tag = "T";
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
                ? "[T]" + super.toString()
                : "[T]" + super.toString() + " (by: " + by + ")";
    }
}