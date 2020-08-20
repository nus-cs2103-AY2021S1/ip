public class Deadline extends Task{
    protected String deadline;

    public Deadline(String taskname, String deadline) {
        super(taskname);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
