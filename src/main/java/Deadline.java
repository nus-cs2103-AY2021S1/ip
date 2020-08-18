public class Deadline extends Task {
    private String byTime;

    Deadline(String deadlineTask, String byTime) {
        super(deadlineTask);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTime + ")";
    }
}
