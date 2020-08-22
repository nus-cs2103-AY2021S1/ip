public class Event extends Task {
    private String time;

    public Event(String details, String time) {
        super(details);
        this.time = time;
    }

    public Event(String details, boolean done, String time) {
        super(details, done);
        this.time = time;
    }

    @Override
    public String store() {
        String done = this.done ? "T " : "F ";
        return "E " + done + this.details + " /at " + this.time;
    }

    @Override
    public String toString() {
        return "[E]" +super.toString() + "(at:" + time + ")";
    }
}
