public class Deadline extends Task {

    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadline(String description, String dueDate, boolean isCompleted) {
        super(description, isCompleted);
        this.dueDate = dueDate;
    }

    @Override
    public Deadline markCompleted() {
        return new Deadline(description, dueDate, true);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
