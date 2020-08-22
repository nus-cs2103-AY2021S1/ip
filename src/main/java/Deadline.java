public class Deadline extends Task {
    private String time;

    public Deadline(String details, String time) {
        super(details);
        this.time = time;
    }

    public Deadline(String details, boolean done, String time) {
        super(details, done);
        this.time = time;
    }

    @Override
    public String store() {
        String done = this.done ? "T " : "F ";
        return "D " + done + this.details + " /by " + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + time + ")";
    }
}
