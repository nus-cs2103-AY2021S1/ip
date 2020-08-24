package duke;

public class Event extends TimedTask {

    public Event(String description, String datetime) {
        super(description, datetime);
    }

    @Override
    protected String getTxtFormat() {
        return "event, " + super.getTxtFormat() + "/at" + this.dateTime.format(Event.inputFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + super.getDateTimeString() + ")";
    }
}
