public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    private DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public DeadlineTask markAsDone() {
        return new DeadlineTask(description, true, deadline);
    }

    @Override
    public String printData() {
        return "D|" + super.printData() + "|" + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
