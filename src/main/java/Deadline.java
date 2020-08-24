public class Deadline extends Task {
    private String taskBy;

    public Deadline(String desc, String by) {
        super(desc);
        this.taskBy = by;
    }

    public String getSaveToFileString() {
        return "D`" + super.getSaveToFileString() + "`" + taskBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.taskBy);
    }
}
