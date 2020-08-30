public class DeadlineTask extends Task {
    protected DukeDate due;

    public DeadlineTask(String description, String due) {
        super(description);
        this.due = new DukeDate(due);
    }

    @Override
    public String fileString() {return "D | " + super.fileString() + " | " + due; }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due + ")";
    }
}
