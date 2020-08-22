public class Event extends Task {
    private String time;
    Event(String item, String time, boolean completed) {
        super(item, completed);
        this.time = time;
    }

    @Override
    public String getItem() {
        return "[E]" + super.getItem() + "(at: " + time + ")";
    }

    public String getTime() {
        return time;
    }
}
