public class Deadline extends Task {
    String deadlineTime;

    Deadline(String taskName, String deadlineTime) {
        super (taskName);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        return "[DEADLINE]" + super.toString() + " | by: " + deadlineTime;
    }
}
