public class DeadlineTask extends Task {
    protected String due;

    public DeadlineTask(String description, String due) {
        super(description);
        this.due = due;
    }
    public DeadlineTask(String description, String due, boolean isCompleted) {
        super(description);
        this.due = due;
        this.isCompleted = isCompleted;
    }

    @Override
    public String fileString() {return "D | " + super.fileString() + " | " + due; }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due + ")";
    }
}
