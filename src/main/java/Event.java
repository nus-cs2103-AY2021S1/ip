public class Event extends Task {
    private String time;

    public Event(String details, String time) {
        super(details);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" +super.toString() + "(at:" + time + ")";
    }
}
