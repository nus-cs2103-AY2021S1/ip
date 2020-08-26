public class Deadlines extends Task {
    private String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    private Deadlines(String description, String deadline, boolean bool) {
        super(description, bool);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public Deadlines markDone() {
        return new Deadlines(this.description, this.deadline, true);
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[D][\u2713] " + this.description + "(by:" + this.deadline + ")";
        } else {
            return "[D][\u2718] " + this.description + "(by:" + this.deadline + ")";
        }
    }
}
