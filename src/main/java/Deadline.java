public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String done, String description, String by) {
        super(description);
        this.by = by;
        this.isDone = done.equals("1");
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
    
    @Override
    public String saveString() {
        return "D" + super.saveString() + ", " + this.by;
    }
}
