public class Event extends TimedTask {

    public Event(String description, String eventTime) {
        super(description, eventTime);
        super.type = Task.Type.EVENT;
    }
}