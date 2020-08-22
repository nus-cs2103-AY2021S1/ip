public class Deadline extends Task {

    protected DukeDateTime by;

    public Deadline(String description, DukeDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toString() + ")";
    }
}
