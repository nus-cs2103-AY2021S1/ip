public class Events extends Task {
    protected String by;

    public Events(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at:" + by + ")";
    }
}
