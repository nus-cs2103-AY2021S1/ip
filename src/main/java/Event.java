public class Event extends TimedTask {

    public Event(String description, String datetime) {
        super(description, datetime);
    }

    @Override
    protected String textFormat() {
        return "event, " + super.textFormat() + "/at" + this.datetime.format(Deadline.inputFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.datetimeString() + ")";
    }
}
