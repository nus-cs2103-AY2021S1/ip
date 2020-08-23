public class Deadline extends Task {

    protected static final String TASK_TYPE = "D";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getSaveFormat() {
        return String.format("%s | %s | %s", Deadline.TASK_TYPE, super.getSaveFormat(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Deadline.TASK_TYPE, super.toString(), this.by);
    }
}