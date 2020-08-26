public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    public String toString() {
        return "[" + this. type + "]" + super.toString() + "(by:" + by + ")";
    }
}