import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, boolean done, String by) {
        super(name, done);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public Deadline setDone(boolean b) {
        return new Deadline(this.getName(),true, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by + ")";
    }
}
