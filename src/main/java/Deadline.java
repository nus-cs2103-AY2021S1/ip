public class Deadline extends Task {
    private String time;

    public Deadline(String details, String time) {
        super(details);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + time + ")";
    }
}
