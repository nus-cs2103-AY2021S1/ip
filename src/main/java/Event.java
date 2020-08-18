public class Event extends Task {
    private String time;
    Event(String item, String time) {
        super(item);
        this.time = time;
    }

    @Override
    public String getItem() {
        return "[E]" + super.getItem() + "(at:" + time + ")";
    }
}
