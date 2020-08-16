public class Deadline extends Task{
    private String time;
    public Deadline(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}
