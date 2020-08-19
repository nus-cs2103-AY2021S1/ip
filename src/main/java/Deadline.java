public class Deadline extends Task {
    // Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
    private String dueBy;
    
    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueBy + ")";
    }
}
