public class Deadline extends Task {

    protected DukeDateTime by;

    public Deadline(String description, DukeDateTime by) {
        super(description);
        this.by = by;
    }

    public DukeDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toString() + ")";
    }
}
