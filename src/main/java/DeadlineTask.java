public class DeadlineTask extends Task {
    protected DukeDate due;

    public DeadlineTask(String description, String due) throws DukeException {
        super(description);
        this.due = new DukeDate(due);
    }
    public DeadlineTask(String description, String due, boolean isCompleted) throws DukeException {
        super(description);
        this.due = new DukeDate(due);
        this.isCompleted = isCompleted;
    }

    @Override
    public String fileString() {return "D | " + super.fileString() + " | " + due; }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due + ")";
    }
}
