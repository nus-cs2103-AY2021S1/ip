public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String getData() {
        return String.format("%s_%s ", super.getData(), deadline);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }
}
