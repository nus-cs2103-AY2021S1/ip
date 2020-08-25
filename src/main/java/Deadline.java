public class Deadline extends Task {
    protected String by;

    public Deadline(String name, boolean isComplete, String by) {
        super(name, isComplete, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String getDetails() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.by);
    }
}
