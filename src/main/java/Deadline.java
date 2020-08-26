import java.util.Date;
public class Deadline extends Task {
    protected Date by;


    public Deadline(String name, boolean isComplete, Date by) {
        super(name, isComplete, TaskType.DEADLINE);
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
