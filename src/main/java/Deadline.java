public class Deadline extends Task {
    String deadlineTime;

    Deadline(String taskName, String deadlineTime) throws DukeException {
        super (taskName);
        this.deadlineTime = deadlineTime.trim();
    }

    @Override
    public String toString() {
        return "[DEADLINE] " + super.toString() + " | by: " + deadlineTime;
    }
}
