public class Event extends TimedTask {

    public Event(String description, String eventTime) {
        super(description, eventTime);
        super.type = Task.Type.EVENT;
    }

    public Event(String description, String eventTime, boolean isDone) {
        super(description, eventTime);
        super.type = Task.Type.EVENT;
        super.isDone = isDone;
    }
}