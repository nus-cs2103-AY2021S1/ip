public class Event extends Task {
    protected String dateAndTime;

    public Event(String description, String dateAndTime) {
        super(description, Type.EVENT);
        this.dateAndTime = dateAndTime;
    }

    public Event(String description, String dateAndTime, boolean isDone) {
        super(description, Type.EVENT, isDone);
        this.dateAndTime = dateAndTime;
    }


    @Override
    public String getTime() {
        return this.dateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateAndTime + ")";
    }
}
