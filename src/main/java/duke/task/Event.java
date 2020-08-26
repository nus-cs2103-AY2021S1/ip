package duke.task;

/**
 * Represents a specific task which is an event.
 */
public class Event extends Task {

    protected LocalDate date = null;
    protected String at = null;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        tag = "E";
        String[] bySplit = at.split(" ", 2);
        date = LocalDate.parse(bySplit[0]);
        if (bySplit.length > 1) {
            this.at = bySplit[1];
        }
    }

    public Event(String description) {
        super(description);
        tag = "E";
    }

    @Override
    public String getTaskType() {
        return tag;
    }

    public String toPrint(){
        return super.toPrint() + "|" + date + "|" + at;
    }

    @Override
    public String toString() {
        return at == null
                ? "[E]" + super.toString()
                : "[E]" + super.toString() + " (by: " + at + ")";
    }
}