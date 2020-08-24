public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
    @Override
    public String toSaveFormat() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
