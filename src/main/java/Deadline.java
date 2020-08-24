public class Deadline extends Task {
    protected String by; //deadline

    public Deadline(String description, String by) {
        super(description, Type.DEADLINE);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, Type.DEADLINE, isDone);
        this.by = by;
    }

    @Override
    public String getTime() {
        return this.by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
