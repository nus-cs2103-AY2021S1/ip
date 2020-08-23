public class Deadline extends Task{
    protected String by;

    public Deadline(String name, String by, boolean done) {
        super(name, done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String writeString() {
        return "D # " + super.writeString() + " # " + by;
    }
}
