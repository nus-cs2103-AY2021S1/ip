public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String time) {
        super(description, isDone);
        this.by = time;
    }
    
    public String getByWhen() {
        return this.by;
    }
    
    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " (by: %s)", by);
    }

}
