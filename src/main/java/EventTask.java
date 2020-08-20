public class EventTask extends Task {
    private String time;

    public EventTask(String summary, String time) {
        super(summary);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + getTime() + ")";
    }
}
