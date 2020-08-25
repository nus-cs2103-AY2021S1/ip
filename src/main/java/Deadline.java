public class Deadline extends Task {

    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDisk() {
        return String.format("deadline\n%s\n%d\n%s", desc, (done == true ? 1 : 0), by);
    }
}