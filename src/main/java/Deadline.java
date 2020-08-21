public class Deadline extends Task {

    private String deadline;

    public static Deadline createNewDeadline(String argument) throws DukeException {
        String[] deadlineArguments = argument.split(" /by ");

        if (deadlineArguments.length != 2) {
            throw new DukeException("Invalid arguments for a new deadline.");
        }

        String deadlineName = deadlineArguments[0];
        if (deadlineName.isBlank()) {
            throw new DukeException("Deadline name cannot be blank!");
        }

        String deadlineTime = deadlineArguments[1];
        if (deadlineTime.isBlank()) {
            throw new DukeException("Deadline time cannot be blank!");
        }

        return new Deadline(deadlineName, deadlineTime);

    }

    private Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String generateStorageString() {
        return String.format("DEADLINE | %s | %s /by %s", isDone ? "TRUE" : "FALSE", taskData, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
