public class Deadline extends Task {

    protected String deadline;

    public Deadline(String new_task, String deadline) {
        super(new_task);
        this.deadline = deadline;
    }

    public String fileFormat() {
        return "D" + " | " + super.fileFormat() + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" +  super.toString() + " (by: " + deadline + ")";
    }
}
