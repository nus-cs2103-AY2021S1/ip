import java.io.Serializable;

public class Deadline extends Task implements Serializable {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by:" + by + ")";
    }
}
