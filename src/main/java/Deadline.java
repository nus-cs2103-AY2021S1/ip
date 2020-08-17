public class Deadline extends Task {
    private String by;

    private Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    protected static Deadline createDeadline(String details) {
        String[] info = details.split("/");
        String desc = info[0];
        String by = info[1].replaceFirst("by ", "");
        return new Deadline(desc, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
