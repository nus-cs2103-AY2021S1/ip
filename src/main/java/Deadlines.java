public class Deadlines extends Task {
    private String by;
    public Deadlines(String description, String cutTime) {
        super(description);
        this.by = cutTime;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
