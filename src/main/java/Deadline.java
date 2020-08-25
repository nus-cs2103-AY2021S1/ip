public class Deadline extends Task {
    private final String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String getStorageFormat() {
        return "D | " + super.getStorageFormat() + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    public static Deadline makeTaskFromInput(String taskName, String deadline) throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badDeadlineTask();
        } else if (deadline.isBlank()) {
            throw DukeException.badDeadlineDate();
        }

        return new Deadline(taskName, deadline);
    }
}
