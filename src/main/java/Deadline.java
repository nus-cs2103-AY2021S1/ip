import java.util.Date;
public class Deadline extends Task {
    protected Date by;

    public Deadline(String name, Date by) {
        super(name);
        this.by = by;
    }

    @Override
    public Date getDate() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), DateFormat.formatDate(this.by));
    }
}
