public class Deadline extends Task {
    
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }
    
    public Deadline(String uniqueId, boolean isDone, String description, String by) {
        super(uniqueId, isDone, description);
        this.by = by;
        this.taskType = "D";
    }
    
    public String getTime() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}