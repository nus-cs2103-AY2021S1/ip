public class Deadline extends Task{
    private String time;
    public Deadline(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    public String dataStorage() {
        return "D | " + (super.getStatus() == "\u2713" ? "1" : "0") + " | " + super.getTaskName() + " | " + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}
