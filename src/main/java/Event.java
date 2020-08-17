public class Event extends Task{
    String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getStatusIcon() {
        return String.format("[D]%s (at: %s)", super.getStatusIcon(), time);
    }
}
