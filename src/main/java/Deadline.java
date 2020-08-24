public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public Deadline markDone() {
        super.markDone();
        return this;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "D | " + doneOrNot + " | " + this.name + " | " + this.by;
    }
}
