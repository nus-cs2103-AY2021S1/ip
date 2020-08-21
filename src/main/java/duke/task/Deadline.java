package duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, int status, String by) {
        super(description, status);
        this.by = by;
    }

    @Override
    public String saveText(int status) {
        return "D | " + status + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
