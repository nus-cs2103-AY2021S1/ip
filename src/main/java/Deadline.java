// partial solution copied from iP
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description.trim());
        this.by = by.trim();
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + " (by: " + by + ")";
    }
}
