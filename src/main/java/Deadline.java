public class Deadline extends Task {
    private String dueTime;

    Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + " " + super.getStatusIcon() + " " + super.description + " (by: " + dueTime + ")";
    }
    
    @Override
    public String getTime() {
        return dueTime;
    }
}
