public class Deadline extends Task {
    protected String by;
    
    static {
        format = "deadline <task description> /by <task deadline>";
    }
    
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
