public class DeadlineTask extends Task {
    protected String due;

    public DeadlineTask(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String fileString() {return "D | " + super.toString() + " | " + due; }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due + ")";
    }
}
