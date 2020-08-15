public class Deadline extends Task {

    protected String deadline;

    protected Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    private String getDeadline() {
        return this.deadline;
    }
}
