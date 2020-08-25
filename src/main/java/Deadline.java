public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    @Override
    public String toWrite() {
        return "D | " + (this.isDone == true ? '1' : '0') + " | " + this.taskDescription + "|" + this.by;
    }
}
