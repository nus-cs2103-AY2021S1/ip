public class Deadline extends Task {
    private String taskBy;

    public Deadline(String desc, String by) {
        super(desc);
        this.taskBy = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.taskBy);
    }
}
