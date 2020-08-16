public class Deadline extends Task {
    String deadline;
    Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s%s", "[D]", super.toString(), "(by: ", deadline + ")");
    }
}
