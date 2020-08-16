public class Event extends Task {
    String taskAt;

    Event(String taskName, String taskAt) {
        super(taskName);
        this.taskAt = taskAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.taskAt + ")";
    }
}