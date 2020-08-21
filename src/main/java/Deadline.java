public class Deadline extends Task {

    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        super.symbol = 'D';
        this.dueDate = dueDate;
    }

    public Deadline(String description, String dueDate, boolean isCompleted) {
        super(description, isCompleted);
        super.symbol = 'D';
        this.dueDate = dueDate;
    }

    @Override
    public Deadline markCompleted() {
        return new Deadline(description, dueDate, true);
    }

    @Override
    public String getStorageString() {
        String baseString = super.getStorageString();
        return String.format("%s | %s", baseString, dueDate);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", symbol, super.toString(), dueDate);
    }
}
