public class DeadlineTask extends Task {
    String deadline;

    public DeadlineTask(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toSaveString() {
        return String.format("T @@ %d @@ %s @@ %s", isDone ? 1 : 0, desc, deadline);
    }
}
