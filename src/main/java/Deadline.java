public class Deadline extends Task {

    private String deadline;
    
    public static Deadline createNewDeadline(String argument) {
        String[] deadlineArguments = argument.split(" /by ");

        String deadlineName = deadlineArguments[0];
        String deadlineTime = deadlineArguments[1];

        return new Deadline(deadlineName, deadlineTime);
    }

    private Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
